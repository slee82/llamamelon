package codegen;

import lexer.Identifier;
import lexer.Type;
import compiler.SymbolTable;

public class StatDef extends Stmt {

    public StatDef(Identifier identifier, Expr expr) {
        this.name = identifier;
        this.expr = expr;
    }
    
    public Type getType() {
        if (ownType == null) {
            throw new RuntimeException("trying to get type before init");
        }
        return this.ownType;
    }

    @Override
    public String code(SymbolTable table) {
        // stats can be overloaded (I suppose so, since variables can, and
        // stats are on the same level as variables, because of body_statement
        if (!table.available(this.name)) {
            throw new RuntimeException("statdef: error: name in use for " + 
                    table.getEntry(this.name));
        }
        
        /*
         * markTable is a symbol table that has .in_stat_decl stored in it. What
         * this means is that the current program view is inside a symbol table.
         * When an expression (atomic expression, that is) encounters a stat
         * identifier, it checks whether it's an expression for a stat
         * declaration.
         * 
         * However, this is not used for stat calls, which are a whole different
         * matter and called differently.
         */
        SymbolTable markTable = new SymbolTable(false, table);
        markTable.putEntry(marker, null);
        markTable.putEntry(argname, table.newID());

        String exprCode = expr.code(markTable);
        // check return val of expression
        if (!expr.getType(markTable).equals(Type.number)) {
            throw new RuntimeException("statdef: expression does not return number");
        }
        
        /*
         * This is how the current stat declaration deduces its own type. Expr
         * code would have checked for consistency of the type using the two
         * hidden names .in_stat_decl and .stat_decl_type.
         * 
         * If a stat uses builtin stats consistently this shouldn't happen.
         * Builtin stats need to be inserted in the symbol table at the earliest
         * possible time, probably in the Program() node.
         */
        if (!markTable.hasEntry(type) ||
                !markTable.hasEntry(argtype) ||
                !markTable.hasEntry(argname)) {
            throw new RuntimeException("statdef: error: stat type cannot be "
                    + "resolved using builtins.");
        }
        // PlayerStat or TeamStat
        Type statType = (Type)markTable.getEntry(type);
        // PlayerObject or TeamObject
        Type argType = (Type)markTable.getEntry(argtype);
        // what name to call the object that will be passed to the expression
        Identifier argName = (Identifier)markTable.getEntry(argname);
        
        this.ownType = statType;
        table.putEntry(this.name, this);
        
        /*
         * <Team|Player>Stat <name> = new <Team|Player>Stat {
         *     public float get(<TeamObj|PlayerObj> <argname>) {
         *         return <expr>;
         *     }
         * }
         */
        return statType.getType() + " " + this.name + " = new " + statType.getType() + " {\n"
            + table.indent + "\t" + "public float get(" + argType.getType() + " " + argName.getID() + ") {\n"
            + table.indent + "\t\treturn " + exprCode + ";\n"
            + table.indent + "\t}\n" + table.indent + "}";
        
        
    }

    private final Identifier name;

    private final Expr expr;
    
    private Type ownType = null;
    
    private final static Identifier marker = new Identifier(".in_stat_decl");
    private final static Identifier type = new Identifier(".stat_decl_type");
    private final static Identifier argtype = new Identifier(".stat_arg_type");
    private final static Identifier argname = new Identifier(".stat_arg_name");

}
