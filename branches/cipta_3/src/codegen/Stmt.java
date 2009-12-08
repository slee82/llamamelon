/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Stmt.java - Code Generator for statements
 */

package codegen;

public abstract class Stmt extends ParseTreeNode {

    int p;

    public abstract void gen();

    public abstract String code();
}
