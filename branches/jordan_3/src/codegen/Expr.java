package codegen;

import lexer.Type;
import compiler.SymbolTable;

public abstract class Expr extends ParseTreeNode {

    public abstract Type getType(SymbolTable table);

}
