/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Program.java - Code Generator for the program
 */

package codegen;
import java.util.LinkedList;

public class Program extends ParseTreeNode {

    public Program(LinkedList stmts, String outname) {
        this.outname = outname;
        this.statements = stmts;
    }

    /**
     * for now, gen() prints to stdout
     */
    public void gen() {
        System.out.println("public class " + outname + " {");
        System.out.println("\tpublic static void main (String args[]) "+
                "throws Exception {");
        
        for (Object each : statements) {
            Stmt cur = (Stmt) each;
	    System.out.print("\t\t");
            cur.gen();
        }
        
        System.out.println("\t}\n}");
    }
    
    private String outname;
    private LinkedList statements;
}
