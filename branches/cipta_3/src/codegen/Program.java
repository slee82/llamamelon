/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Program.java - Code Generator for the program
 */

package codegen;

import java.util.LinkedList;

import compiler.SymbolTable;

public class Program extends ParseTreeNode {

    public Program(LinkedList<Stmt> stmts, String outname,
            LinkedList<Declaration> varDeclarations, SymbolTable table) {
        this.outname = outname;
        this.statements = stmts;
        this.varDeclarations = varDeclarations;
        this.table = table;
    }

    public String code() {
        throw new RuntimeException(
                "Programs shouldn't be collected as strings.");
    }

    /**
     * for now, gen() prints to stdout
     */
    public void gen() {
        System.out.println("public class " + outname + " {\n");

        for (Declaration each : varDeclarations) {
            System.out.print("\t");
            each.gen();
            System.out.print("\n");
        }

        System.out.println("\tpublic static void main (String args[]) "
                + "throws Exception {");

        for (Stmt each : statements) {
            Stmt cur = each;
            System.out.print("\t\t");
            cur.gen();
        }

        System.out.println("\t}\n");
        
        /*
         * FUNCTION DEFINITIONS
         */
        for (Object each : table.getVals()) {
            if (each instanceof FuncDef) {
                FuncDef define = (FuncDef) each;
                System.out.println(define.code());
            }
        }
        
        System.out.println("}");
    }

    private String outname;
    
    private SymbolTable table;

    private LinkedList<Stmt> statements;

    private LinkedList<Declaration> varDeclarations;
}
