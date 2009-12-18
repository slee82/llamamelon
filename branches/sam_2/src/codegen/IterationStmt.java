/**
 * 
 */
package codegen;

import lexer.Identifier;
import lexer.Type;
import compiler.SymbolTable;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * @author 
 * 
 */
public class IterationStmt extends Stmt {

   
    public IterationStmt(LinkedList<Stmt> bodylist) {
    	this.bodylist = bodylist;
    }
	public IterationStmt(Expr expr, LinkedList<Stmt> bodylist) {
    	this.expr = expr;
    	this.bodylist = bodylist;
    	
    }
    
    public IterationStmt(Identifier ident1, Identifier ident2, LinkedList<Stmt> bodylist){
    	this.element = ident1;
    	this.collection = ident2;
    	this.bodylist = bodylist;
    }

  
    @Override
    public String stmtCode(SymbolTable table) {
    	if (expr == null) {
    		String loopCode = "while (true) { \n";
    		
    		SymbolTable inTable = new SymbolTable(true, table);
    		Iterator<Stmt> bodyIter = bodylist.iterator();
    		while (bodyIter.hasNext()) {
    			Stmt cur = bodyIter.next();
    			loopCode += cur.code(inTable) + "\n"; // code for each statement    
    		}
    		
    		loopCode += table.indent() + "}";
    		return table.indent() + loopCode;
    	}
    	
    	if (expr != null) {
    		String loopCode = "float x = (float) 0; \n";
    		loopCode += table.indent() + "while ( x < ";
    		loopCode += expr.code(table); 
    		loopCode += ") { \n";  
    		
    		SymbolTable inTable = new SymbolTable(true, table);
    		Iterator<Stmt> bodyIter = bodylist.iterator();
    		while (bodyIter.hasNext()) {
    			Stmt cur = bodyIter.next();
    			loopCode += cur.code(inTable) + "\n"; // code for each statement    
    		}
        
    		loopCode += table.indent() + "x++; \n";
    		loopCode += table.indent() + "}";
        
    		return table.indent() + loopCode;
    	}
    	// dummy for now
    	String loopCode = "";
    	return table.indent() + loopCode;
    	
    }
    
   
    Expr expr;
    Identifier element, collection;
    LinkedList<Stmt> bodylist;
}
