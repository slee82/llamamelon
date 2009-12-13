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

    public FuncDef() {
    }
    
    @Override
    public String stmtCode(SymbolTable table) {
        // check if name is still available
        if (table.hasEntry(this.name)) {
            throw new RuntimeException("funcdef: the name " + name + 
                    " in use for " + table.getEntry(this.name));
        }
        
        table.putEntry(this.name, this);
        // make the code with respect to the current program view
        // that is, only know variables and functions already declared till now
        this.global = this.makeGlobalCode(table);
        return table.indent() + "/* function " + name + " moved outside main(). */";
    }
    
    public String globalCode() {
        return this.global;
    }
    
    protected String makeGlobalCode(SymbolTable table) {
        /*
         * Check return type with body list
         */
        // TODO: add code for checking return type

        // make as if for main, so push back indenting one tab
        table.decreaseIndent(1);
        
        String begin = table.indent() + privileges + " " + scope + " " +
            this.retType.getType() + " " + this.name.getID()
            + " (" + this.plistCode() + " ) {\n";
        
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
            begin += cur.code(inTable) + "\n"; // code for each statement    
        }
        
        begin += table.indent() + "}";
        
        table.increaseIndent(1);
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
        if (name.getID().equals("sim")) {
            name = new Identifier("Simulator.sim");
            return true;
        }
        return false;
    }
    
    protected String global = null;

    Identifier name;

    Type retType;

    LinkedHashMap<Identifier, Type> paramlist;

    LinkedList<Stmt> bodylist;

    String privileges = "private";

    String scope = "static";


}
