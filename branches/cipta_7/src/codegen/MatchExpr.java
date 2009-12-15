package codegen;

import lexer.Identifier;
import lexer.NumericConst;
import lexer.Type;

import compiler.SymbolTable;

public class MatchExpr extends Expr {
    
    public MatchExpr(Expr arg, Expr list) {
        this.arg = arg;
        this.list = list;
    }

    @Override
    public Type getType(SymbolTable table) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    /**
     * In the spec, the order in which from operands are defined is implementation
     * dependent, here it's matcher first, then the list.
     * 
     * The argument is evaluated only once and it is matched against every
     * element, stopping at the first match and returning that.
     * 
     * If none of the elements match, from returns a "null" or 0, depending on
     * data type.
     */
    public String code(SymbolTable table) {
        InsertionPoint insert = table.getIP();
        
        String argcode = arg.code(table); // evaluated only once
        String listcode = list.code(table);
        
        Type listType = list.getType(table);
        if (!(listType instanceof ListType))
            throw new RuntimeException("match: right hand must be a list");
        Type contents = ((ListType)listType).contents;
        
        /*
         * <retType> <rettok> = <0/null>
         * for (<retType> <eachtok> : <list>) {
         *      if (<eachtok>.match(<arg>) { // or <eachtok> == <arg> for nums
         *          <rettok> = <eachtok>;
         *          break;
         *      }
         * }
         * ----- in the expr -----
         * <rettok>
         */
        Identifier rettok = table.newID();
        Identifier eachtok = table.newID();
        // put in table so it doesn't get generated again when it's still not safe
        table.putEntry(rettok, this);
        table.putEntry(eachtok, this);
        
        String head = table.indent() + contents.getType() + " " + rettok.getID() + " = " +
            (contents.equals(Type.number) ? "((float) 0)" : "null") + ";\n";
        head += table.indent() + "for (" + contents.getType() + " " + eachtok.getID() +
            " : " + listcode + ") {\n";
        insert.insert(head);
        
        String inloop = table.indent() + "\tif (";
        if (contents.equals(Type.number)) {
            inloop += eachtok.getID() + " == " + argcode;
        } else if (contents.equals(Type.string)) {
            inloop += eachtok.getID() + ".equals(" + argcode + ")";
        } else {
            inloop += eachtok.getID() + ".match(" + argcode + ")";
        } // if (blah.match(blah))
        inloop += ") {\n";
        insert.insert(inloop);
        
        inloop = table.indent() + "\t\t" + rettok.getID() + " = " + eachtok.getID() + ";\n";
        inloop += table.indent() + "\t\tbreak;\n";
        inloop += table.indent() + "\t}\n";
        inloop += table.indent() + "}\n";
        insert.insert(inloop);
        
        return rettok.getID();
    }

    private Expr arg;
    private Expr list;

}
