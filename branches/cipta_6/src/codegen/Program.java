/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Program.java - Code Generator for the program
 */

package codegen;

import java.util.LinkedList;
import lexer.Identifier;
import lexer.Type;
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
        System.out.println(
                "import javabackend.*;\n" + 
                "import java.util.Arrays;\n" + 
                "\npublic class " + outname + " {\n");
        
        // collect variable declarations here
        LinkedList<Declaration> varDeclarations = new LinkedList<Declaration>();
        
        /* INPUT BUILTIN STATS */
        table.putEntry(new Identifier("W"), 
                new BuiltinStatDef(new Identifier("TeamObj.W"), Type.teamStat));
        table.putEntry(new Identifier("L"), 
                new BuiltinStatDef(new Identifier("TeamObj.L"), Type.teamStat));
        table.putEntry(new Identifier("AB"), 
                new BuiltinStatDef(new Identifier("PlayerObj.AB"), Type.playerStat));
        table.putEntry(new Identifier("R"), 
                new BuiltinStatDef(new Identifier("PlayerObj.R"), Type.playerStat));
        table.putEntry(new Identifier("H"), 
                new BuiltinStatDef(new Identifier("PlayerObj.H"), Type.playerStat));
        table.putEntry(new Identifier("DBL"), 
                new BuiltinStatDef(new Identifier("PlayerObj.DBL"), Type.playerStat));
        table.putEntry(new Identifier("TPL"), 
                new BuiltinStatDef(new Identifier("PlayerObj.TPL"), Type.playerStat));
        table.putEntry(new Identifier("HR"), 
                new BuiltinStatDef(new Identifier("PlayerObj.HR"), Type.playerStat));
        table.putEntry(new Identifier("BB"), 
                new BuiltinStatDef(new Identifier("PlayerObj.BB"), Type.playerStat));

        System.out.println(table.indent() + "public static void main (String args[]) "
                + "throws Exception {");
        
        table.increaseIndent(1);

        for (Stmt each : statements) {
            Stmt cur = each;
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
        
        table.decreaseIndent(1);

        System.out.println(table.indent() + "}"); // end main()
        

        /*
         * FUNCTION DEFINITIONS
         */
        System.out.println();
        for (Object each : table.getVals()) {
            if (each instanceof FuncDef) {
                FuncDef define = (FuncDef) each;
                // retreive delayed code
                System.out.println(define.globalCode() + "\n");
            }
        }

        /*
         * DECLARATIONS
         */
        for (Declaration each : varDeclarations) {
            each.genGlobalDecl(table);
        }
        
        System.out.println("}");
    }

    private String outname;

    private LinkedList<Stmt> statements;
}
