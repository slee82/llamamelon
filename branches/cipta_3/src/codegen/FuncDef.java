package codegen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import compiler.SymbolTable;

import lexer.Identifier;
import lexer.Type;

public class FuncDef extends Stmt {

    public FuncDef(Identifier name, Type retType, HashMap<Identifier,Type> paramlist, LinkedList<Stmt> bodylist) {
        
        this.name = name;
        this.retType = retType;
        this.paramlist = paramlist;
        this.bodylist = bodylist;
    }
    
    @Override
    public String code(SymbolTable table) {
        /*
         * LINE BELOW MEANS FUNCTION ACCESSIBLE ONLY AFTER DECL IN .BALL file.
         */
        table.putEntry(this.name, this);
        return "/* function " + name + " moved outside main(). */";
    }
    
    public String globalCode(SymbolTable table) {
        /*
         * Check return type with body list
         */
        // TODO: add code for checking return type
        
        String begin = "\n\tprivate static " + this.retType.getType() + " " + this.name.getID()
            + " (" + FuncDef.plistCode(this.paramlist) + " ) {";
        
        String line = "\n\t\t"; // indentation
        
        Iterator<Stmt> bodyIter = bodylist.iterator();
        while (bodyIter.hasNext()) {
            Stmt cur = bodyIter.next();
            begin += (line + cur.code(table)); // code for each statement    
        }
        
        begin += "\n\t}";
        return begin;
    }

    private static String plistCode(HashMap<Identifier, Type> paramlist2) {
        String res = "";
        for (Identifier id : paramlist2.keySet()) {
            res += ", " + paramlist2.get(id).getType() + " " + id.getID();
        }

        return res.substring(1);
    }

    Identifier name;

    Type retType;

    HashMap<Identifier, Type> paramlist;

    LinkedList<Stmt> bodylist;


}
