package codegen;

import compiler.SymbolTable;

import lexer.Identifier;
import lexer.Type;

public class BuiltinStatDef extends StatDef {

    public BuiltinStatDef(Identifier identifier, Type type) {
        super(identifier, new StatExpr());
        if (!(type.equals(Type.playerStat) || type.equals(Type.teamStat))) {
            throw new IllegalArgumentException("error: builtin stats must " 
                    + "be of type playerStat or teamStat");
        }
        this.type = type;
    }
    
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String code(SymbolTable table) {
        // can actually be silenced, just let us know
        return "/* ERROR: trying to get definition of a builtin stat */";
    }
    
    private final Type type;

}
