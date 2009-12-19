package codegen;

import java.util.LinkedHashMap;

import lexer.Identifier;
import lexer.Type;

import compiler.SymbolTable;

public class BuiltinFuncDef extends FuncDef {
    
    public BuiltinFuncDef(Identifier identifier, Type ret, Type[] sig) {
        super.name = identifier;
        super.retType = ret;
        super.paramlist = new LinkedHashMap<Identifier, Type>();
        int arg = 0;
        for (Type t : sig) {
            paramlist.put(new Identifier("arg_" + arg), t);
        }
    }

    public String stmtCode(SymbolTable table) {
        throw new RuntimeException("error: trying to fetch definition of builtin " + name);
    }
    
    public String globalCode() {
        throw new RuntimeException("error: trying to fetch definition of builtin " + name);
    }
    
    protected String makeGlobalCode(SymbolTable table) {
        throw new RuntimeException("error: trying to fetch definition of builtin " + name);
    }

    // Set the correct name for built-in functions
    public boolean checkBuiltIn() {
        return true;
    }
    

}