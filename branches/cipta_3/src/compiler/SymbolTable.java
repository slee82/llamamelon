/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * SymbolTable.java - Symbol Table definition and methods
 */
package compiler;

import lexer.*;

import java.util.HashMap;

/**
 * Symbol table manages identifiers. Each instance corresponds to names present
 * in either the top level (global vars), each function, or a block. Lookup is
 * done by checking the table first, and then the table designated as the "goto"
 * table, meaning the symbol table one level up. There are some issues:
 * 
 * 1. It's pretty obvious that one symbol table exists for global variables +
 * functions, and subsequent tables for functions. But what about blocks like
 * loops and conditionals where variable names must be unique wrt the function,
 * but must be deleted when the block ends?
 * 
 * An easy solution is to have a field like "isTopTable" where the symbol table
 * represents an end for checking name uniqueness.
 * 
 * Variables are stored with identifiers as key and Declaration as value.
 * Functions are stored with identifiers as key and FuncDef object as value.
 */
public class SymbolTable {

    /**
     * Complete constructor. Takes a boolean parameter (where "false" limits the
     * uniqueness search to this table, top level and function tables must set
     * this), and a symbol table to go to if an identifier isn't found here.
     * 
     * @param isTop
     * @param nextLookup
     */
    public SymbolTable(boolean isTop, SymbolTable nextLookup) {
        this.table = new HashMap<Token, Object>();
        this.isTopTable = isTop;
        this.nextLookup = nextLookup;
    }

    public SymbolTable(boolean isTop) {
        this(isTop, null);
    }

    public void putEntry(Token key, Object val) {
        table.put(key, val);
    }

    /**
     * Keep looking backwards (towards more outer declarations) for a matching
     * token. Don't specify type, because this also checks whether the object
     * the user is referring to is valid.
     * 
     * @param key what Token (usually identifier) needs to be searched
     * @return null if not found, the Object if found (may still be null)
     */
    public Object getEntry(Token key) {
        if (table.containsKey(key)) {
            return table.get(key);
        }
        // stop at top level
        if (this.nextLookup == null) {
            return null;
        }
        return this.nextLookup.getEntry(key);
    }

    public Object[] getVals() {
        return table.values().toArray();
    }

    /**
     * @param id
     * @param type
     * @return true if an identifier with name specified by id and type
     *         specified by type can be made in the current table.
     */
    public boolean available(Token id) {
        if (table.containsKey(id)) return false;
        if (isTopTable) return true;
        return nextLookup.available(id);
    }

    private HashMap<Token, Object> table;

    // cannot change the chain
    public final SymbolTable nextLookup;

    public final boolean isTopTable;

}
