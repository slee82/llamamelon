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
        table.putEntry(new Identifier("2B"), 
                new BuiltinStatDef(new Identifier("PlayerObj.DBL"), Type.playerStat));
        table.putEntry(new Identifier("3B"), 
                new BuiltinStatDef(new Identifier("PlayerObj.TPL"), Type.playerStat));
        table.putEntry(new Identifier("HR"), 
                new BuiltinStatDef(new Identifier("PlayerObj.HR"), Type.playerStat));
        table.putEntry(new Identifier("BB"), 
                new BuiltinStatDef(new Identifier("PlayerObj.BB"), Type.playerStat));
        table.putEntry(new Identifier("ER"),
        		new BuiltinStatDef(new Identifier("PlayerObj.ER"), Type.playerStat));
        table.putEntry(new Identifier("IP"),
        		new BuiltinStatDef(new Identifier("PlayerObj.IP"), Type.playerStat));
        table.putEntry(new Identifier("K"),
        		new BuiltinStatDef(new Identifier("PlayerObj.K"), Type.playerStat));
        
        /* INPUT BUILTIN LIST-STATS */
        table.putEntry(new Identifier("PLAYERS"),
        		new BuiltinStatListDef(new Identifier("TeamObj.PLAYERS"), Type.list));
        table.putEntry(new Identifier("PITCHERS"),
        		new BuiltinStatListDef(new Identifier("TeamObj.PITCHERS"), Type.list));
        table.putEntry(new Identifier("BATTERS"),
        		new BuiltinStatListDef(new Identifier("TeamObj.BATTERS"), Type.list));
        
        /* INPUT BUILTIN STRING ATTRIBUTES */
        table.putEntry(new Identifier("name"),
        		new BuiltinAttributeDef(new Identifier("PlayerObj.name"), Type.string));
        
        /* INPUT BUILTIN FUNCTIONS */
        table.putEntry(new Identifier("load"),
                new BuiltinFuncDef(new Identifier("Loader.load"), Type.team,
                        new Type[] { Type.string }));
        table.putEntry(new Identifier("sim"),
                new BuiltinFuncDef(new Identifier("Simulator.sim"), Type.team,
                        new Type[] { Type.team, Type.team, Type.number }));
        table.putEntry(new Identifier("isBatter"),
                new BuiltinFuncDef(new Identifier("PlayerObj.isBatter"), Type.bool,
                        new Type[] { Type.player }));
        table.putEntry(new Identifier("isPitcher"),
                new BuiltinFuncDef(new Identifier("PlayerObj.isPitcher"), Type.bool,
                        new Type[] { Type.player }));

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
            if (each instanceof FuncDef && !(each instanceof BuiltinFuncDef)) {
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
