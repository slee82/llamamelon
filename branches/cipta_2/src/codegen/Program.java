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
    public String code() {
        String result = "public class " + outname + " {\n";
        result += "\tpublic static void main (String args[]) "+
                "throws Exception {\n";
        
        for (Object each : statements) {
            Stmt cur = (Stmt) each;
            result += "\t\t";
            result += cur.code();
        }
        
        result += "\t}\n}\n";
        return result;
    }
    
    private String outname;
    private LinkedList statements;
}
