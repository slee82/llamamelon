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
            cur.gen();
        }
        
        System.out.println("\t}\n}");
    }
    
    private String outname;
    private LinkedList statements;
}
