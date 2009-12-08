/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * ParseTreeNode.java
 */

package codegen;

public abstract class ParseTreeNode {
    
    public void gen() {
        System.out.println(this.code());
    }
    
    public abstract String code();
}
