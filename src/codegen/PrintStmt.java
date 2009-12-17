/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * PrintStmt.java - Code Generator for print statements
 */

package codegen;

import compiler.SymbolTable;

public class PrintStmt extends Stmt {

    public PrintStmt(Expr expr) {
        this.toprint = expr;
    }
    
    public String stmtCode(SymbolTable table) {
        String begin = table.indent() + "System.out.println(";
        begin += toprint.code(table);
        begin += (");");
        
        // if expr is of type number (float) 
        
        String thisType = toprint.getType(table).toString();
        //Type thisType = toprint.getType(table);
        // System.out.println(thisType);
        //ltype.equals(Type.number)
        
        if (thisType.equals("<type 'number'>")){
        	begin = "";
        	begin = table.indent() + "System.out.println(Tools.fixFloat(";
        	begin += toprint.code(table);
        	begin += ("));");
        			
        }
        
        return begin;
    }
    
    private Expr toprint;

}
