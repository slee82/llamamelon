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
	
	int i;	
	for(i=0; i<identifiers.size(); i++){	//set the identifier string to id1, id2, ...
		Identifier id  = (Identifier) identifiers.get(i).get(0);
		
		if(i>0)	//dont put a comma at the beginning
			this.identifier += "," + id.getID();
		else
			this.identifier = id.getID();
		
		//check is the last identifier has an = sign for assignment
		if(i == identifiers.size()-1 && identifiers.get(i).size() > 1){
			this.val = (Expr)identifiers.get(i).get(1);
		}
	}
    }
    
    //generate the declaration
    public void gen() {
        System.out.print(type + " " + identifier);
	if(val != null){		//if the variable is to be initialized
		System.out.print(" = ");
        	val.gen();
	}
        System.out.println(";");
    }
    
    private String type;
    private String identifier;
    private Expr val = null;

}
