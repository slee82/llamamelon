package codegen;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import lexer.Identifier;
import lexer.Type;

public class SimFuncDef extends FuncDef {

    public SimFuncDef(Identifier name, LinkedList<Stmt> bodylist) {
        super.name = name;
        super.retType = new Type("team");

	LinkedHashMap<Identifier, Type> parameters = new LinkedHashMap<Identifier, Type>();
	parameters.put(new Identifier("team1"), new Type("team"));
	parameters.put(new Identifier("team2"), new Type("team"));
	
        super.paramlist = parameters;
        super.bodylist = bodylist;

        if (checkBuiltIn()) {
            throw new RuntimeException("error: simfunction name is used as builtin.");
        }
    }

}
