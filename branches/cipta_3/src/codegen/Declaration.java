package codegen;
/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Declaration.java - Code Generator for variable declarations
 */

import lexer.*;
import java.util.ArrayList;

public class Declaration extends Stmt {

    // Constructor: Get Type and Identifiers
    public Declaration(Type type, ArrayList<ArrayList> identifiers) {
        this.type = type.getType(); // extract the Strings
        idexpPairs = identifiers;
    }

    // generate the declaration
    public String code() {
        String begin = (type + " ");

        int i;
        for (i = 0; i < idexpPairs.size(); i++) { // set the identifier string
                                                    // to id1, id2, ...
            ArrayList ar = (ArrayList) idexpPairs.get(i);
            Identifier id = (Identifier) ar.get(0);

            if (i > 0) // dont put a comma at the beginning
                begin += ("," + id.getID());
            else
                begin += (id.getID());

            // check is the last identifier has an = sign for assignment
            if (ar.size() > 1) {
                Expr x = (Expr) ar.get(1);
                begin += (" = ");
                begin += x.code();
            }
        }

        begin += (";\n");
        return begin;
    }

    private String type;

    private ArrayList idexpPairs;

}
