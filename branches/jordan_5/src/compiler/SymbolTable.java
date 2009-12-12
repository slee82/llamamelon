/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * SymbolTable.java - Symbol Table definition and methods
 */
package compiler;

import lexer.*;

import java.util.HashMap;
import java.util.Random;

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
        this.table = new HashMap<Identifier, Object>();
        this.isTopTable = isTop;
        this.nextLookup = nextLookup;
        // unused for now, but can be used later for dynamically figuring out
        // indentation
        this.hops = nextLookup == null ? 0 : nextLookup.hops + 1;
        this.indent = nextLookup == null ? "\t" : nextLookup.indent + "\t";
    }

    public SymbolTable(boolean isTop) {
        this(isTop, null);
    }

    public void putEntry(Identifier key, Object val) {
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
    public Object getEntry(Identifier key) {
        if (table.containsKey(key)) {
            return table.get(key);
        }
        // stop at top level
        if (this.nextLookup == null) {
            return null;
        }
        return this.nextLookup.getEntry(key);
    }
    

    public boolean hasEntry(Identifier name) {
        if (table.containsKey(name)) return true;
        if (this.nextLookup == null) return false;
        return this.nextLookup.hasEntry(name);
    }
    
    public Object[] getVals() {
        return table.values().toArray();
    }

    public Object[] getKeys() {
        return table.keySet().toArray();
    }
    
    /**
     * @param id
     * @param type
     * @return true if an identifier with name specified by id and type
     *         specified by type can be made in the current table.
     */
    public boolean available(Identifier id) {
        if (table.get(id) instanceof Identifier) return false;
        if (isTopTable) return true;
        if (nextLookup == null) return true;
        return nextLookup.available(id);
    }

    public int size() {
        return table.size();
    }

    /**
     * Creates a new identifier that is unique throughout the symbol table.
     * That is, does not conflict with any existing names or hide any name.
     * @return the new Identifier, not yet added to the table.
     */
    public Identifier newID() {
        Identifier longest = this.getLongestKey();
        // in case of builtins like Loader.load -> _loader_load
        // longer than longest ID means it can't be the same
        if (longest == null || longest.getID().length() <= 4) {
            // too short/no previous ones, generate random
            Random r = new Random();
            String token = "tok_" + String.format("%x",Math.abs(r.nextInt()));
            longest = new Identifier(token);
        }
        
        String newID = "_" + longest.getID().replace('.', '_').toLowerCase();
        Identifier res = new Identifier(newID);
        
        // shouldn't happen
        if (this.hasEntry(res))
            throw new RuntimeException("ERROR: newID() result " + res + " in table.");
        return res;
    }

    public String indent() {
        return this.indent;
    }
    
    /*
     * Because I'm lazy, this function is currently recursive.
     */
    public void increaseIndent(int i) {
        if (i == 0) return;
        this.indent = this.indent + "\t";
        increaseIndent(i-1);
    }
    
    /*
     * Because I'm lazy, this function is also currently recursive.
     */
    public void decreaseIndent(int i) {
        if (i == 0) return;
        this.indent = this.indent.substring(1);
        decreaseIndent(i-1);
    }

    /**
     * Useful when creating a new identifier.
     * @return the identifier with the longest name, or possibly null
     */
    private Identifier getLongestKey() {
        Identifier curLongest = null;
        for (Identifier id : this.table.keySet()) {
            if (curLongest == null ||
                    id.getID().length() > curLongest.getID().length()) {
                curLongest = id;
            }
        }
        
        if (this.nextLookup == null) return curLongest;
        
        Identifier upID = this.nextLookup.getLongestKey();
        if (upID.getID().length() > curLongest.getID().length()) return upID;
        return curLongest;
    }

    private HashMap<Identifier, Object> table;

    // cannot change the chain
    public final SymbolTable nextLookup;
    
    // how many hops until the base, used for java indentation.
    public final int hops;
    
    private String indent;

    public final boolean isTopTable;

}
