package codegen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javabackend.TeamObj;

import compiler.SymbolTable;

import lexer.Identifier;
import lexer.Type;

public class SimFuncDef extends FuncDef {

    public SimFuncDef(Identifier name, LinkedList<Stmt> bodylist) {

        /*
         * We want the FuncDef class to use the "doSim" identifier, to
         * implement the SimFunction interface
         */
        super.name = new Identifier("doSim");
        super.retType = new Type("team");
        super.privileges = "public";
        super.scope = "";
        /* We still need the name of the simulation fuction for the new SimFunction class.*/
        this.simName = name;

        /* Tell FuncDef what parameters we are using (these are always fixed) */
        LinkedHashMap<Identifier, Type> parameters = new LinkedHashMap<Identifier, Type>();
        parameters.put(new Identifier("team1"), new Type("team"));
        parameters.put(new Identifier("team2"), new Type("team"));

        super.paramlist = parameters;
        super.bodylist = bodylist;

    }

    @Override
    public String stmtCode(SymbolTable table) {
        if (table.hasEntry(this.simName)) {
            throw new RuntimeException("simdef: simfunction name " + simName + " in use.");
        }

        table.putEntry(this.simName, this);
        // make the code with respect to the current program view
        // that is, only know variables and functions already declared till now
        global = this.makeGlobalCode(table);
        return table.indent() + "/* simfunction " + simName + " moved outside main(). */";
    }

    @Override
    protected String makeGlobalCode(SymbolTable table) {
        table.decreaseIndent(1);
        
        /* Ecapsulate the simulation function inside a SimFunction interface */
        String begin = table.indent() + "private final static SimFunction " + 
            simName.getID() + " = new SimFunction() {\n";
        begin += table.indent() + "\tpublic TeamObj doSim(TeamObj team1, TeamObj team2) {\n";

        // create new bindings in parameter
        SymbolTable inTable = new SymbolTable(true, table);
        inTable.increaseIndent(1);
        
        for (Identifier param : this.paramlist.keySet()) {
            // implicitly make declarations
            Object[] decl_contents = new Object[2];
            // Type param
            decl_contents[0] = param;
            decl_contents[1] = null;

            ArrayList<Object[]> temp = new ArrayList<Object[]>();
            temp.add(decl_contents);

            Declaration newdec = new Declaration(this.paramlist.get(param),
                    temp);
            inTable.putEntry(param, newdec);
        }

        
        Iterator<Stmt> bodyIter = bodylist.iterator();
        while (bodyIter.hasNext()) {
            Stmt cur = bodyIter.next();
            begin += cur.code(inTable) + "\n"; // code for each statement    
        }
        
        begin += table.indent() + "\t}\n";
        begin += table.indent() + "};";
        
        table.increaseIndent(1);
        return begin;
    }

    Identifier simName;

}
