package codegen;

import compiler.SymbolTable;

import lexer.*;

public class StopdoStmt extends Stmt {

    
    public StopdoStmt() {
       
    }

   
  
    @Override
    public String stmtCode(SymbolTable table) {
        
        String stmt = "break; ";
       
        return table.indent() + stmt;
    }

}