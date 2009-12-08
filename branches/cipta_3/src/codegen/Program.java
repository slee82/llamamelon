/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Program.java - Code Generator for the program
 */

package codegen;

import java.util.LinkedList;

import compiler.SymbolTable;

public class Program extends ParseTreeNode {

    public Program(LinkedList<Stmt> stmts, String outname) {
        this.outname = outname;
        this.statements = stmts;
    }

    public String code(SymbolTable table) {
        throw new RuntimeException(
                "Programs shouldn't be collected as strings.");
    }

    /**
     * for now, gen() prints to stdout
     */
    public void gen(SymbolTable table) {
        System.out.println("public class " + outname + " {\n");
        
        // collect variable declarations here
        LinkedList<Declaration> varDeclarations = new LinkedList<Declaration>();

        System.out.println("\tpublic static void main (String args[]) "
                + "throws Exception {");

        for (Stmt each : statements) {
            Stmt cur = each;
            System.out.print("\t\t");
            // if statement is a declaration, treat specially
            if (cur instanceof Declaration) {
                // delay to global variable
                Declaration d_cur = (Declaration)cur;
                varDeclarations.add(d_cur);
                d_cur.genGlobalMain(table);
            } else {
                cur.gen(table);
            }
        }

        for (Declaration each : varDeclarations) {
            System.out.print("\t");
            each.gen(table);
            System.out.print("\n");
        }

        System.out.println("\t}\n");
        
        /*
         * FUNCTION DEFINITIONS
         */
        for (Object each : table.getVals()) {
            if (each instanceof FuncDef) {
                FuncDef define = (FuncDef) each;
                System.out.println(define.globalCode(table));
            }
        }
        
        System.out.println("}");
    }

    private String outname;

    private LinkedList<Stmt> statements;
}
