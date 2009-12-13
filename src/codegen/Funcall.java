/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Funcall.java - Code Generator for function calls
 */

package codegen;

import lexer.*;
import java.util.ArrayList;
import compiler.SymbolTable;

public class Funcall extends Expr {

    /* First constructor (with arguments) */
    public Funcall(Identifier name, ArrayList<Expr> args) {
        this.name = name;
        this.args = args;
    }

    /* Second constructor (no args) */
    public Funcall(Identifier name) {
        this.name = name;
    }

    public Type getType(SymbolTable table) {
        /*
         * Check function return types, etc
         */
        Type built = checkBuiltIn();
        if (built != null)
            return built;

        Object def = table.getEntry(name);
        if (!(def instanceof FuncDef)) {
            throw new RuntimeException("funcall: identifier " + name
                    + " invalid, either nonexistent or not a function");
        }
        FuncDef define = (FuncDef) def;
        return define.retType;

    }

    /*
     * Create the code for the function call.
     * 
     * Remember, the expressions have no side effects, which means the call
     * itself has to be moved back to its own statement and assignment, because
     * the function might change some variables up.
     */
    public String code(SymbolTable table) {
        InsertionPoint insert = table.getIP();
        Type retType = getType(table);
        changeBuiltIn();
        String begin = (name.getID() + "(");
        Identifier tempID = table.newID();

        if (args != null) { // print out all the args
            int i;
            for (i = 0; i < args.size(); i++) {
                if (i > 0)
                    begin += (","); // comma separated
                begin += args.get(i).code(table); // call gen() of each
                // argument
            }
        }
        begin += (")");

        insert.insert(table.indent() + retType.getType() + " " + tempID.getID()
                + " = " + begin + ";\n");

        return tempID.getID();
    }

    private void changeBuiltIn() {
        if (name.equals(new Identifier("load"))) {
            this.name = new Identifier("Loader.load");
        }
        if (name.equals(new Identifier("sim"))) {
            this.name = new Identifier("Simulator.sim");
        }
    }

    // Set the correct name for built-in functions
    public Type checkBuiltIn() {
        if (name.equals(new Identifier("load"))) {
            return new Type("team");
        }
        if (name.equals(new Identifier("sim"))) {
            return new Type("team");
        }
        return null;
    }

    private ArrayList<Expr> args = null;

    private Identifier name;

}
