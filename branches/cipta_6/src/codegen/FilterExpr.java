package codegen;

import lexer.Identifier;
import lexer.Type;

import compiler.SymbolTable;

/**
 * FILTERING A LIST
 * ================
 * 1. left expr must be a list
 * 2. right expr must be a boolean
 * 3. right expr can have special token '$$' which means the item being checked
 *
 */
public class FilterExpr extends Expr {
    
    public FilterExpr(Expr list, Expr filter) {
        this.list = list;
        this.filter = filter;
    }

    @Override
    public Type getType(SymbolTable table) {
        return Type.list;
    }

    @Override
    public String code(SymbolTable table) {
        if (!list.getType(table).equals(Type.list)) {
            throw new RuntimeException("filter: left hand expression must evaluate to a list.");
        }
        
        // create temp binding of '$$'
        SymbolTable inTable = new SymbolTable(false, table);
        if (inTable.hasEntry(inRef)) {
            throw new RuntimeException("filter: cannot make a 'where' expr inside another");
        }
        if (!filter.getType(table).equals(Type.bool)) {
            throw new RuntimeException("filter: parenthesized expression must evaluate to boolean.");
        }
        return null;
    }
    
    private final Identifier inRef = new Identifier("$$");
    
    private Expr list, filter;

}
