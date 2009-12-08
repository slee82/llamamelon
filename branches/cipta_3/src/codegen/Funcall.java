/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * Funcall.java - Code Generator for function calls
 */

package codegen;
import lexer.*;
import java.util.ArrayList;

public class Funcall extends Expr {

    /*First constructor (with arguments)*/
    public Funcall(Identifier name, ArrayList<Expr> args) {
	this.name = name;
	this.args = args;
    }

    /*Second constructor (no args)*/
    public Funcall(Identifier name) {
	this.name = name;
    }
    
    /*Generate the code*/
    public void gen() {
	checkBuiltIn();

        System.out.print(name.getID() + "(");
	if(args!=null){	//print out all the args
		int i;        	
		for(i=0; i<args.size(); i++){
			if(i>0)
				System.out.print(",");	//comma separated
			args.get(i).gen();	//call gen() of each argument
		}
	}
        System.out.print(")");
    }

    //Set the correct name for built-in functions
    public void checkBuiltIn(){
	if(name.getID().equals("load"))
		name.setID("Loader.load");
    }
    
    private ArrayList<Expr> args = null;
    private Identifier name;

}
