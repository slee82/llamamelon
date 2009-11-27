/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * ball.y - Parser specification of the BALL language (using BYACC/J)
 */

%{
import java.io.*;
import java.util.LinkedList;
import lexer.*;
import codegen.*;

@SuppressWarnings("unchecked")
%}

/* 
 * ===============================================================
 *  Token declarations for communication between lexer and parser
 * ===============================================================
 */

%token STRING
%token SEMICOLON
%token PRINT

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
        top.gen();
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
body_statement : print_statement { $$ = $1; }
;

/**PRINT_STATEMENT**/
print_statement : 
    PRINT expression SEMICOLON {
        $$ = new ParserVal(new PrintStmt((Expr)$2.obj));
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
;

/*ATOM_EXPRESSION*/
atom_expression : 
    STRING { 
        System.err.println("got string " + $1.obj); 
        $$ = new ParserVal(new Expr((StringConst)(val_peek(0).obj)));
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

/* that's how you use the parser */
public static void main(String args[]) throws IOException {
	if (args.length == 0) {
		System.err.println("no arguments");
		System.exit(0);
	}
    
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
    
	Parser yyparser = new Parser(new FileReader(args[0]), 
            new SymbolTable(), name);
	yyparser.yyparse();
}

