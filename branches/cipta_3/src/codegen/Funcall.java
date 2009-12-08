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

    /* Generate the code */
    public String code(SymbolTable table) {
        checkBuiltIn();
        
        String begin = (name.getID() + "(");
        if (args != null) { // print out all the args
            int i;
            for (i = 0; i < args.size(); i++) {
                if (i > 0)
                    begin += (","); // comma separated
                begin += args.get(i).code(table); // call gen() of each argument
            }
        }
        begin += (")");
        return begin;
    }

    // Set the correct name for built-in functions
    public void checkBuiltIn() {
        if (name.getID().equals("load"))
            name.setID("Loader.load");
    }

    private ArrayList<Expr> args = null;

    private Identifier name;

}
