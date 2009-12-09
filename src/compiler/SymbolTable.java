/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * SymbolTable.java - Symbol Table definition and methods
 */
package compiler;

import lexer.*;
import java.util.HashMap;

/**
 * 
 */
public class SymbolTable {

    private HashMap<Token,Object> table;
    
    public SymbolTable() {
        table = new HashMap<Token, Object>();
    }
    
    public void putEntry(Token key, Object val) {
        table.put(key, val);
    }
    
}
