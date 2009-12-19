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
    
    public IterationStmt(Identifier ident1, Expr ident2, LinkedList<Stmt> bodylist){
    	this.element = ident1;
    	this.collection = ident2;
    	this.bodylist = bodylist;
    }

    
    @Override
    public String stmtCode(SymbolTable table) {
    	
    	if (expr == null && element == null && collection == null) {
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
    	//String strExpr = expr.code(table);
    	//Type thisType = expr.getType(table);
    	//System.out.println(thisType);
    	if (expr != null) {
    		//System.out.println(strExpr);
    		//System.out.println(isNumber(strExpr));
    		/*if ( !isNumber(strExpr)) {
    			
    			throw new RuntimeException ("Incorrect expression type "+strExpr+" needs to be number");
    		}*/
    		
    		/*if(! ltype.equals(rtype)) {
        		throw new RuntimeException("expr: type mismatch " + valueL.getType(table) + " and " + valueR.getType(table));
    		*/
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
    	//System.out.println(element.getID());
    	//System.out.println(collection.code(table));
    	if (element != null && collection != null)
    	{
    		/*Object elementIdent = table.getEntry(element);
            if (!(element instanceof Declaraion)) {
                throw new RuntimeException("foreach: identifier " + element
                        + " invalid, either nonexistent or not an identifier");
            }*/
    		//System.out.println(element.getID());
            //begin += name.getID();
    		//System.out.println(element);
    		String loopCode = "for ( float "+ element.getID()+" : "+collection.code(table)+" ) {\n";
    		//String loopCode +="while (elementIter.hasNext()) { \n";
    		
    		SymbolTable inTable = new SymbolTable(true, table);
    		Iterator<Stmt> bodyIter = bodylist.iterator();
    		while (bodyIter.hasNext()) {
    			Stmt cur = bodyIter.next();
    			loopCode += cur.code(inTable) + "\n"; // code for each statement    
    		}
    		    loopCode +="}\n";
    		    return table.indent() + loopCode;
    	}
    	// dummy for now
    	String loopCode = "";
    	return table.indent() + loopCode;
    	
    }
    
    public boolean isNumber(String num){
        try{
            Float.parseFloat(num);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
   
    Expr expr, collection;
    Identifier element;
    LinkedList<Stmt> bodylist;
}
