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
            throwErr("identifier " + name.getID()
                    + " invalid, either nonexistent or not a simfunction", name.getID());
        }

        String begin = "Simulator.theSimFunction = ";
        begin += name.getID();
        begin += (";");
        return table.indent() + begin;
    }
    
    private Identifier name;

}
