/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * PrintStmt.java - Code Generator for print statements
 */

package codegen;

import compiler.SymbolTable;
import lexer.Type;

public class PrintStmt extends Stmt {

    public PrintStmt(Expr expr) {
        this.toprint = expr;
    }
    
    public String stmtCode(SymbolTable table) {
        String begin = table.indent() + "System.out.println(";
        begin += toprint.code(table);
        begin += (");");
        
        // if expr is of type number (float)   
        Type thisType = toprint.getType(table);
        if (thisType.equals(Type.number)){
        	begin = "";
        	begin = table.indent() + "System.out.println(Tools.fixFloat(";
        	begin += toprint.code(table);
        	begin += ("));");
        			
        }
        
        return begin;
    }
    
    private Expr toprint;

}
