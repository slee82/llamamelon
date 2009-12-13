/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Stmt.java - Code Generator for statements
 */

package codegen;

import compiler.SymbolTable;

public abstract class Stmt extends ParseTreeNode implements InsertionPoint {
    
    /**
     * By default, inserts done after a previous insert are APPENDED to the
     * insert chain (meaning the latest insert is closest to the original
     * statement)
     */
    public void insert(String code) {
        insert += code;
    }
    
    /**
     * Will most likely get overwritten 
     */
    public String code(SymbolTable table) {
        table.setInsertPt(this);
        return insert + code(table);
    }
    
    String insert = "";

}
