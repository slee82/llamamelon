package codegen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

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

        if (checkBuiltIn()) {
            throw new RuntimeException("error: simfunction name is used as builtin.");
        }
    }

    protected String makeGlobalCode(SymbolTable table) {
	/* Ecapsulate the simulation function inside a SimFunction interface */
        String begin = "\tprivate static SimFunction " + simName.getID() + " = new SimFunction(){";
	begin += super.makeGlobalCode(table).replaceAll("\n", "\n\t");
	begin += "\n\t};";
	return begin;
    }

    Identifier simName;

}
