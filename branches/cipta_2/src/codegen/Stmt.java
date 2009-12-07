/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Stmt.java - Code Generator for statements
 */

package codegen;

public class Stmt extends ParseTreeNode {

    int p;

    public void gen() {
        System.out.println(this.code());
    }

    public String code() {
        // TODO Auto-generated method stub
        return ";";
    }
}
