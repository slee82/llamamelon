/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Declaration.java - Code Generator for variable declarations
 */

package codegen;
import lexer.*;
import java.util.ArrayList;

public class Declaration extends Stmt {

    //Constructor: Get Type and Identifiers
    public Declaration(Type type, ArrayList<ArrayList> identifiers) {
	this.type = type.getType();		//extract the Strings
	idexpPairs = identifiers;
    }
    
    //generate the declaration
    public void gen() {
        System.out.print("static " + type + " ");
	
	int i;
	for(i=0; i<idexpPairs.size(); i++){	//set the identifier string to id1, id2, ...
		ArrayList ar = (ArrayList) idexpPairs.get(i);
		Identifier id  = (Identifier) ar.get(0);
		
		if(i>0)	//dont put a comma at the beginning
			System.out.print("," + id.getID());
		else
			System.out.print(id.getID());
		
		//check is the last identifier has an = sign for assignment
		if(ar.size() > 1){
			Expr x = (Expr) ar.get(1);
			System.out.print(" = ");
			x.gen();
		}
	}

        System.out.println(";");
    }
    
    private String type;
    private ArrayList idexpPairs;

}
