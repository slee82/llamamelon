package codegen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import lexer.Identifier;
import lexer.Type;

public class FuncDef extends Stmt {

    public FuncDef(Identifier name, Type retType, HashMap<Identifier, Type> paramlist, LinkedList<Stmt> bodylist) {
        /*
         * Check return type with body list
         */
        for (Stmt inner : bodylist) {
            if (inner instanceof ReturnStmt) {
                ReturnStmt ret = (ReturnStmt) inner;
                if (!(ret.type.equals(retType))) {
                    throw new IllegalArgumentException("funcdef: conflicting return type in function "+ 
                            name +", \n    expected " + retType + ", but got " + ret.type);
                }
            }
        }
        // all OK
        this.name = name;
        this.retType = retType;
        this.paramlist = paramlist;
        this.bodylist = bodylist;
    }
    
    public void gen() {
        System.out.println("        /* function " + name + " moved outside main(). */");
    }
    
    public String code() {
        // private static <type> <name> 
        String begin = "\n    private static " + this.retType.getType() + " " + this.name.getID()
            + " (" + FuncDef.plistCode(this.paramlist) + " ) {";
        
        String line = "\n        "; // indentation
        
        Iterator<Stmt> bodyIter = bodylist.iterator();
        while (bodyIter.hasNext()) {
            Stmt cur = bodyIter.next();
            begin += (line + cur.code()); // code for each statement    
        }
        
        begin += "\n    }";
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
