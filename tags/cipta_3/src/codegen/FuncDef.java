package codegen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import compiler.SymbolTable;

import lexer.Identifier;
import lexer.Type;

public class FuncDef extends Stmt {

    public FuncDef(Identifier name, Type retType, LinkedHashMap<Identifier,Type> paramlist, LinkedList<Stmt> bodylist) {
        this.name = name;
        this.retType = retType;
        this.paramlist = paramlist;
        this.bodylist = bodylist;

        if (checkBuiltIn()) {
            throw new RuntimeException("error: function name is used as builtin.");
        }
    }
    
    @Override
    public String code(SymbolTable table) {
        table.putEntry(this.name, this);
        return "/* function " + name + " moved outside main(). */";
    }
    
    public String globalCode(SymbolTable table) {
        /*
         * Check return type with body list
         */
        // TODO: add code for checking return type
        
        String begin = "\n\tprivate static " + this.retType.getType() + " " + this.name.getID()
            + " (" + this.plistCode() + " ) {";
        
        String line = "\n\t\t"; // indentation
        
        // create new bindings in parameter
        SymbolTable inTable = new SymbolTable(true, table);
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
            begin += (line + cur.code(inTable)); // code for each statement    
        }
        
        begin += "\n\t}";
        return begin;
    }

    private String plistCode() {
        String res = "";
        for (Identifier id : paramlist.keySet()) {
            res += ", " + paramlist.get(id).getType() + " " + id.getID();
        }

        return res.substring(1);
    }
    
    // Set the correct name for built-in functions
    public boolean checkBuiltIn() {
        if (name.getID().equals("load")) {
            name = new Identifier("Loader.load");
            return true;
        }
        return false;
    }

    Identifier name;

    Type retType;

    LinkedHashMap<Identifier, Type> paramlist;

    LinkedList<Stmt> bodylist;


}
