/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * ball.y - Parser specification of the BALL language (using BYACC/J)
 */

%{
import java.io.*;
import java.util.LinkedList;
import java.util.ArrayList;
import lexer.*;
import codegen.*;

/* necessary because ParserVal deals with generics */
@SuppressWarnings("unchecked")
%}

/* 
 * ===============================================================
 *  Token declarations for communication between lexer and parser
 * ===============================================================
 */

%token STRING
%token IDENTIFIER
%token NUMBER
%token SEMICOLON
%token EQL
%token COMMA
%token OPAREN
%token CPAREN
%token PRINT
%token TYPE

%%

/* 
 * ==================
 *  Language Grammar
 * ==================
 */

/***PROGRAM***/
program : 
    statement_list { 
        System.err.println("adding node for _program_");
        LinkedList<Stmt> stlist = (LinkedList<Stmt>)$1.obj;
        Program top = new Program(stlist, outname);
        top.gen(); // moves to intermediate code generation stage
    }
;

statement_list : 
    statement {
        LinkedList<Stmt> newlist = new LinkedList<Stmt>();
        newlist.addLast((Stmt)$1.obj);
        $$ = new ParserVal(newlist);
    }
    | statement_list statement {
        LinkedList<Stmt> cur = (LinkedList<Stmt>)$1.obj;
        cur.addLast((Stmt)$2.obj);
        $$ = new ParserVal(cur);
    }
;

statement : body_statement { $$ = $1; }
;

/*Body Statements are all statements except function declarations*/
body_statement : declaration { $$ = $1; }
	| print_statement { $$ = $1; }
;

/**PRINT_STATEMENT**/
print_statement : 
    PRINT expression SEMICOLON {
        $$ = new ParserVal(new PrintStmt((Expr)$2.obj));
    }
;

/**DECLARATION**/
declaration : TYPE variable_declarators SEMICOLON {
		$$ = new ParserVal(new Declaration((Type)$1.obj, (ArrayList)$2.obj));
		}
;

variable_declarators : variable_declarator {
			ArrayList<ArrayList> a = new ArrayList<ArrayList>();
			a.add((ArrayList)$1.obj);
			$$ = new ParserVal (a);
		     }
                     | variable_declarators COMMA variable_declarator {
			ArrayList<ArrayList> a = new ArrayList<ArrayList>();
			a.addAll((ArrayList)$1.obj);
			a.add((ArrayList)$3.obj);
			$$ = new ParserVal (a);
		     }
;

variable_declarator : IDENTIFIER {
			ArrayList a = new ArrayList();
			a.add($1.obj);
			$$ = new ParserVal (a);
		    }
                    | IDENTIFIER EQL expression {
			ArrayList a = new ArrayList();
			a.add($1.obj);
			a.add($3.obj);
			$$ = new ParserVal (a);
		    }
;

/*EXPRESSION*/
expression : logical_or_expression { $$ = $1; }
;

/*LOGICAL*/
logical_or_expression : logical_and_expression { $$ = $1; }
;

logical_and_expression : logical_not_expression { $$ = $1; }
;

logical_not_expression : comparison_expression { $$ = $1; }
;

/*COMPARISON*/
comparison_expression : addition_expression { $$ = $1; }
;

/*ARITHMETIC*/
addition_expression : multiplication_expression { $$ = $1; }
;

multiplication_expression : unary_expression { $$ = $1; }
;

/*UNARY*/
unary_expression : postfix_expression { $$ = $1; }
;

/*POSTFIX*/
postfix_expression : primary_expression { $$ = $1; }
;

/*PRIMARY*/
primary_expression : atom_expression { $$ = $1; }
		   | function_call { $$ = $1; }
;

/*FUNCTION_CALL*/
function_call : IDENTIFIER OPAREN CPAREN {
		$$ = new ParserVal(new Funcall((Identifier)$1.obj));
	      }	
              | IDENTIFIER OPAREN argument_list CPAREN {
		$$ = new ParserVal(new Funcall((Identifier)$1.obj, (ArrayList<Expr>)$3.obj));
	      }	
              ;

argument_list : expression {
		ArrayList<Expr> a = new ArrayList<Expr>();
		a.add((Expr)$1.obj);
		$$ = new ParserVal(a);
	      }
              | argument_list COMMA expression {
		ArrayList<Expr> a = new ArrayList<Expr>();
		a.addAll((ArrayList<Expr>)$1.obj);
		a.add((Expr)$3.obj);
		$$ = new ParserVal(a);
	      }
              ;


/*ATOM_EXPRESSION*/
atom_expression : STRING { 
        		System.err.println("got string " + $1.obj); 
        		$$ = new ParserVal(new Expr((StringConst)($1.obj)));
    		}
		| IDENTIFIER {
			$$ = new ParserVal(new Expr((Identifier)($1.obj)));
		}
		| NUMBER {
			
		}
;

%%

/* 
 * ====================================================================
 *  Other enhancements to the parser class, plus constructors and main
 * ====================================================================
 */

/* a reference to the lexer object */
private Yylex lexer;
  
/* a reference to the symbol table */
private SymbolTable table;

/* what to call the class */
private String outname;

/* interface to the lexer */
private int yylex () {
	int yyl_return = -1;
    try {
		yyl_return = lexer.yylex();
    }
    catch (IOException e) {
		System.err.println("yylex: IO error :"+e);
    }
	return yyl_return;
}

/* error reporting */
public void yyerror (String error) {
	System.err.println ("Error: " + error);
}

/* lexer is created in the constructor */
public Parser(Reader r, SymbolTable table, String out) {
    lexer = new Yylex(r, this, table);
    this.table = table;
    outname = out;
}

/* 
 * ===============
 *  Main function
 * ===============
 */


public static void main(String args[]) throws IOException {
	if (args.length == 0) {
		System.err.println("no arguments");
		System.exit(0);
	}
    
    /*
     * Determine the name of the output file
     */
    String name = args[0];
    name = name.substring(name.lastIndexOf('/')+1);
    
    int val = name.lastIndexOf('.');
    while (val != -1) {
        name = name.substring(0, val);
        val = name.lastIndexOf('.');
    }
    
    if (name.length() < 1) {
        System.err.println("invalid name");
        System.exit(1);
    }
    
    /*
     * create the parser 
     */
	Parser yyparser = new Parser(new FileReader(args[0]), 
            new SymbolTable(), name);
	yyparser.yyparse();
}

