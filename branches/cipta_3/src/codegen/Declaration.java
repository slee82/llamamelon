package codegen;

/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Declaration.java - Code Generator for variable declarations
 */

import lexer.*;
import java.util.ArrayList;

import compiler.SymbolTable;

public class Declaration extends Stmt {

    // Constructor: Get Type and Identifiers
    public Declaration(Type type, ArrayList<ArrayList> identifiers) {
        this.type = type; // extract the Strings
        idexpPairs = identifiers;
    }

    // generate the declaration
    public String code(SymbolTable table) {
        String begin = (type.getType() + " ");

        int i;
        for (i = 0; i < idexpPairs.size(); i++) { // set the identifier string
            // to id1, id2, ...
            ArrayList ar = (ArrayList) idexpPairs.get(i);
            Identifier id = (Identifier) ar.get(0);
            if (!table.available(id)) {
                throw new RuntimeException("declaration: identifier " + id + " in use.");
            }

            if (i > 0) // dont put a comma at the beginning
                begin += ("," + id.getID());
            else
                begin += (id.getID());

            // check is the last identifier has an = sign for assignment
            if (ar.size() > 1) {
                
                Expr x = (Expr) ar.get(1);
                // check if the expression returns the correct type
                if (!(x.getType(table).equals(this.type))) {
                    throw new RuntimeException("declaration: expression type " + 
                            x.getType(table) + " does not match type; expected " 
                            + this.type);
                }
                
                begin += (" = ");
                begin += x.code(table);
            }
        }

        begin += (";\n");
        return begin;
    }

    /**
     * Treat this function as a global variable, and print the initialization in
     * main().
     * 
     * number p = 3, b = 5.4; ==> p = 3;
     *                            b = 5.4;
     * (genGlobalDecl takes care of the declarations)
     */
    public void genGlobalMain(SymbolTable table) {
        String begin = "";

        int i;
        for (i = 0; i < idexpPairs.size(); i++) { // set the identifier string
            // to id1, id2, ...
            ArrayList ar = (ArrayList) idexpPairs.get(i);
            Identifier id = (Identifier) ar.get(0);
            
            if (!table.available(id)) {
                throw new RuntimeException("declaration: identifier " + id + " in use.");
            }

            // check is the last identifier has an = sign for assignment
            if (ar.size() > 1) {
                begin += (id.getID());
                
                Expr x = (Expr) ar.get(1);
                if (!(x.getType(table).equals(this.type))) {
                    throw new RuntimeException("declaration: expression type " + 
                            x.getType(table) + " does not match type; expected " 
                            + this.type);
                }
                
                begin += " = ";
                begin += x.code(table);
                begin += ";\n";
                
                table.putEntry(id, this);
            }
        }
        
        System.out.print(begin);
    }
    
    /**
     * 
     * @param table
     */
    public void genGlobalDecl(SymbolTable table) {
        String begin = (type.getType() + " ");

        int i;
        for (i = 0; i < idexpPairs.size(); i++) { // set the identifier string
            // to id1, id2, ...
            ArrayList ar = (ArrayList) idexpPairs.get(i);
            Identifier id = (Identifier) ar.get(0);
            
            // check if all identifiers are entered correctly in the table
            if (!((table.getEntry(id)) == this)) {
                throw new RuntimeException("declaration: identifier " + id + " mismatched.");
            }

            if (i > 0) // dont put a comma at the beginning
                begin += ("," + id.getID());
            else
                begin += (id.getID());
        }

        begin += (";\n");
        System.out.println(begin);
    }

    private Type type;

    private ArrayList idexpPairs;

}
