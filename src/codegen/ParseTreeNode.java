/*
 * COMS W4115 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
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
}
