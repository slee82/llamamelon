/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * ActivateStmt.java - Code Generator for activate statements
 */

package codegen;
import lexer.Identifier;

import compiler.SymbolTable;

public class ActivateStmt extends Stmt {

    public ActivateStmt(Identifier theName) {
        this.name = theName;
    }
    
    public String stmtCode(SymbolTable table) {
        Object def = table.getEntry(name);
        if (!(def instanceof SimFuncDef)) {
            throw new RuntimeException("activate: identifier " + name
                    + " invalid, either nonexistent or not a simfunction");
        }

        String begin = "Simulator.theSimFunction = ";
        begin += name.getID();
        begin += (";");
        return begin;
    }
    
    private Identifier name;

}
