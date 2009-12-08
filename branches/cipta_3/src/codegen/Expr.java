/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Expr.java - Code Generator for expressions
 */

package codegen;

import compiler.SymbolTable;

import lexer.*;

/**
 * Implements atomic expressions.
 * 
 * An expression has a type associated with it, which can be output by
 * getType(). In case the expression is an identifier, a symbol table is needed to figure out what 
 */
public class Expr extends ParseTreeNode {

    public Expr(StringConst const1) {
        this.stringval = const1.val;
        type = new Type("string");
    }

    public Expr(Identifier id) {
        this.stringval = id.getID();
        this.ident = id;
        // TODO: find out identifier type through symbol table.
        type = new Type("identifier");
    }

    public Expr(NumericConst n) {
        this.stringval = n.val;
        type = new Type("number");
    }

    public Expr() {
    }
    
    public Type getType(SymbolTable table) {
        if (this.type.val.equals("identifier")) {
            Object val = table.getEntry(this.ident);
            if (val == null) {
                throw new RuntimeException("expr: unknown identifier " + ident);
            } else if (val instanceof FuncDef) {
                throw new RuntimeException("expr: identifier " + ident 
                        + " is a function.");
            }
            return (Type)val;
        }
        return new Type(this.type.val);
    }

    /**
     * Outputs the Java code for this expression to the standard output.
     */
    public void gen(SymbolTable table) {
        // later needs to differ by type
        System.out.print(this.code(table));
    }

    /**
     * @return Java code for this expression.
     */
    public String code(SymbolTable table) {
        return this.stringval;
    }

    public String toString() {
        return stringval;
    }

    private Type type;

    private String stringval;
    private Identifier ident = null;

    public static final int STRINGCONST = 1;

    public static final int IDENTIFIER = 2;

    public static final int NUMERICCONST = 3;
}
