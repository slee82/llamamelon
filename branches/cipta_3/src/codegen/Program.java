/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Program.java - Code Generator for the program
 */

package codegen;
import java.util.LinkedList;

public class Program extends ParseTreeNode {

    public Program(LinkedList<Stmt> stmts, String outname, LinkedList<Declaration> varDeclarations) {
        this.outname = outname;
        this.statements = stmts;
	this.varDeclarations = varDeclarations;
    }

    /**
     * for now, gen() prints to stdout
     */
    public void gen() {
        System.out.println("public class " + outname + " {\n");
	
	for (Declaration each : varDeclarations){
	    	System.out.print("\t");
		each.gen();
		System.out.print("\n");
	}

        System.out.println("\tpublic static void main (String args[]) "+
                "throws Exception {");
        
        for (Stmt each : statements) {
            Stmt cur = each;
	    System.out.print("\t\t");
            cur.gen();
        }
        
        System.out.println("\t}\n}");
    }
    
    private String outname;
    private LinkedList<Stmt> statements;
    private LinkedList<Declaration> varDeclarations;
}
