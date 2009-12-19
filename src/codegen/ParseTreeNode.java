/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * ParseTreeNode.java
 */

package codegen;

import compiler.SymbolTable;

public abstract class ParseTreeNode {
    
    public void gen(SymbolTable table) {
        System.out.println(this.code(table));
    }
    
    public abstract String code(SymbolTable table);

    public void setLine(int line){
	lineNo = line;
    }

    public RuntimeException throwErr(String err, String tok){
	String prepare = "\n==================ERROR==================\n*" + this.getClass().getName()
		+ "*" + " - " + err + "\nAT LINE: " + lineNo
		+ ", NEAR: '" + tok + "'." + "\n==================ERROR==================";
	RuntimeException rtx = new RuntimeException(prepare);
	throw rtx;
    }

    public RuntimeException throwErr(String err){
	String prepare = "\n==================ERROR==================\n*" + this.getClass().getName()
		+ "*" + " - " + err + "\nAT LINE: " + lineNo;
	RuntimeException rtx = new RuntimeException(prepare);
	throw rtx;
    }

    protected int lineNo = 0;
}
