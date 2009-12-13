package codegen;

import lexer.Identifier;
import lexer.Type;

import compiler.SymbolTable;

/**
 * FILTERING A LIST
 * ================
 * 1. left expr must be a list
 * 2. right expr must be a boolean
 * 3. right expr can have special token '$$' which means the item being checked
 *
 */
public class FilterExpr extends Expr {
    
    public FilterExpr(Expr list, Expr filter) {
        this.list = list;
        this.filter = filter;
    }

    @Override
    public Type getType(SymbolTable table) {
        Type lt = list.getType(table);
        
        if (!(lt instanceof ListType)) {
            throw new RuntimeException("filter: left hand expression must evaluate to a list.");
        }
        ListType ltype = (ListType)lt;
        return ltype;
    }
    
    public static class SelfKeyword extends AtomicExpr {
        public Type getType(SymbolTable table) {
            if (table.hasEntry(inTyp))
                return (Type)table.getEntry(inTyp);
            throw new RuntimeException("self: keyword placed not on a 'where' search");
        }

        /**
         * @return Java code for this expression.
         */
        public String code(SymbolTable table) {
            if (table.hasEntry(inRef))
                return ((Identifier)table.getEntry(inRef)).getID();
            throw new RuntimeException("self: keyword placed not on a 'where' search");
        }
    }

    @Override
    public String code(SymbolTable table) {
        InsertionPoint insert = table.getIP();
        
        getType(table);
        Type lt = list.getType(table);
        
        if (!(lt instanceof ListType)) {
            throw new RuntimeException("filter: left hand expression must evaluate to a list.");
        }
        ListType ltype = (ListType)getType(table);
        Type cont = ltype.contents;
        
        Identifier oldlist = table.newID();
        table.putEntry(oldlist, oldlist);
        Identifier newlist = table.newID();
        table.putEntry(newlist, newlist);
        Identifier each = table.newID();
        table.putEntry(each, each);
        // BallList<...> oldlist = < expr code >
        // BallList<...> newlist = new BallList();
        // for (T each : BallList) {
        //     if (< where code, with 'self' replaced with 'each'>)
        //         newlist.addEnd(each);
        // }
        // <original expr, with newlist
        
        // note that this makes using '++' and '--' impossible.
        
        insert.insert(table.indent() + lt.getType() + " " + oldlist.getID() + " = " + list.code(table) + ";\n");
        insert.insert(table.indent() + lt.getType() + " " + newlist.getID() + " = new " + lt.getType() + "();\n");
        insert.insert(table.indent() + "for (" + cont.getType() + " " + each.getID() + " : " + oldlist.getID() + ") {\n");
        
        SymbolTable inTable = new SymbolTable(true, table);
        inTable.putEntry(inRef, each);
        inTable.putEntry(inTyp, cont);
        insert.insert(
                inTable.indent() + "if (" + filter.code(inTable) + ")\n" +
                    inTable.indent() + "\t" + newlist.getID() + ".add(" + each.getID() + ");\n");
        
        insert.insert(table.indent() + "}\n");
        
        return newlist.getID();
    }
        
    private static final Identifier inRef = new Identifier("self");
    private static final Identifier inTyp = new Identifier(".self_type");
    
    private Expr list, filter;

}
