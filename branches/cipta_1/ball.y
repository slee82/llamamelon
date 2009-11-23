/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * ball.y - Parser specification of the BALL language (using BYACC/J)
 */

%{
import java.io.*;
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
program : statement_list  { System.out.println(""); }
;

statement_list : statement
               | statement_list statement
;

statement : body_statement
;

/*Body Statements are all statements except function declarations*/
body_statement : print_statement
;

/**PRINT_STATEMENT**/
print_statement : PRINT expression SEMICOLON
;

/*EXPRESSION*/
expression : logical_or_expression
;

/*LOGICAL*/
logical_or_expression : logical_and_expression
;

logical_and_expression : logical_not_expression
;

logical_not_expression : comparison_expression
;

/*COMPARISON*/
comparison_expression : addition_expression
;

/*ARITHMETIC*/
addition_expression : multiplication_expression
;

multiplication_expression : unary_expression
;

/*UNARY*/
unary_expression : postfix_expression
;

/*POSTFIX*/
postfix_expression : primary_expression
;

/*PRIMARY*/
primary_expression : atom_expression
;

/*ATOM_EXPRESSION*/
atom_expression : STRING { System.out.println("got string " + $1.sval); }
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
public Parser(Reader r, SymbolTable table) {
    lexer = new Yylex(r, this, table);
    this.table = table;
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
	Parser yyparser = new Parser(new FileReader(args[0]), new SymbolTable());
	yyparser.yyparse();
}

