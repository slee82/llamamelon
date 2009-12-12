/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * ball.y - Parser specification of the BALL language (using BYACC/J)
 */

%{
import java.io.*;
import java.util.*;
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
%token COLON
%token IS, ISNOT, GT, LT, GTE, LTE
%token AND, OR, NOT
%token EQL, PLUSEQL, MINEQL, MULTEQL, DIVEQL, MODEQL
%token PLUS, MIN, MULT, DIV, MOD
%token COMMA
%token OPAREN
%token CPAREN
%token PRINT
%token ACTIVATE
%token FUNCTION
%token SIMFUNCTION
%token STAT
%token RETURN
%token RETURNS
%token IS
%token TYPE
%token END

%%

/* 
 * ==================
 *  Language Grammar
 * ==================
 */

/*
 * What the heck is going on in here!?
 * ===================================
 * 
 * To put it simple, all the grammar code does is laying out the structure of 
 * the parse tree. No error checking is involved here. Only after the whole 
 * parse tree is complete will error checking and code generation start.
 * Therefore, gen() now takes a symbol table, because that's the phase that is
 * going to need it the most.
 */

/*
 * REMEMBER: ALL CODE CHECKING AND GENERATION STARTS AFTER TOP.GEN(TABLE). what
 * the byacc/j code does is just to prepare the program structure.
 */


/*** PROGRAM ***/
/*
 * This is the top node of the program. If the token sequence can be derived
 * from this node, a Java program can be spit out by the compiler.
 */
program : 
    statement_list { 
        System.err.println("adding node for _program_");
        LinkedList<Stmt> stlist = (LinkedList<Stmt>)$1.obj;
        Program top = new Program(stlist, outname);
        top.gen(table); // moves to intermediate code generation stage
    }
;

/*
 * A statement list collects a sequence of statements inside a linked list (for
 * easy sequential reading). The way the grammar works is that it first seeks
 * the head of the statement list (by peeling off statements one by one from the
 * end to the beginning). Then, as the list is propagated up the calls, it is
 * appended with each "peel" of the list.
 * 
 * This kind of strategy when dealing with sequences of nodes that are all
 * similar (like parameter lists, etc) is found throughout the grammar.
 */
statement_list : 
    statement { // reached beginning of statement list
        LinkedList<Stmt> newlist = new LinkedList<Stmt>();
        newlist.addLast((Stmt)$1.obj);
        $$ = new ParserVal(newlist);
    }
    | statement_list statement { // add as we propagate up
        LinkedList<Stmt> cur = (LinkedList<Stmt>)$1.obj;
        cur.addLast((Stmt)$2.obj);
        $$ = new ParserVal(cur);
    }
;

/*
 * This represents a top-level statement. Since BALL has no main(), top-level
 * statements can be anything from a print commant, assignment, function def,
 * etc. BALL must then sort this to create a proper java program while
 * preserving the meaning of the original source.
 * 
 * However, body statements, that is those that can appear in function bodies,
 * loops or conditional blocks, cannot contain function definitions (nested
 * func definitions are not supported by the BALL language).
 */
statement : 
    body_statement { $$ = $1; }
    | function_definition { $$ = $1; }
    | sim_function_definition { $$ = $1; }
;

/*
 * Similar to statement_list, but specifically for body statements, that is
 * function bodies, if blocks, et cetera.
 */
body_statement_list : 
    body_statement {
        LinkedList<Stmt> newlist = new LinkedList<Stmt>();
        newlist.addLast((Stmt)$1.obj);
        $$ = new ParserVal(newlist);
    }
    | body_statement_list body_statement {
        LinkedList<Stmt> cur = (LinkedList<Stmt>)$1.obj;
        cur.addLast((Stmt)$2.obj);
        $$ = new ParserVal(cur);
    }
;

/*
 * Body Statements are all statements except function declarations. Having
 * function declarations not on the top level (corresponding to the 'class'
 * level of the java output) is not supported by the BALL language. 
 */
body_statement : 
      declaration { $$ = $1; }
    | stat_declaration { $$ = $1; }
    | expression_statement { $$ = $1; }
    | print_statement { $$ = $1; }
    | jump_statement { $$ = $1; }
    | assignment_statement { $$ = $1; }
	| activate_statement { $$ = $1; }
;

/** FUNCTION_DEFINITION **/

/*
 * In BALL, function definitions can only happen in the top level. Naturally,
 * the only variables they'll get access to is global variables, parameters,
 * and variables declared in the body itself. 
 */
function_definition :
    FUNCTION IDENTIFIER OPAREN parameter_list CPAREN RETURNS TYPE COLON END {
        System.err.println("parser: function definition");
    
        Identifier name = (Identifier)$2.obj;
    
        /*
         * Special caution here on the hash implementation used for parameter
         * list.
         * 
         * In BALL, the parameter list of the function behaves like Java. That
         * is, the parameters' order is also important in distinguishing a
         * function. Therefore, the LinkedHashMap class is used instead of the
         * plain HashMap which may not preserve order.
         */
        LinkedHashMap<Identifier,Type> paramlist = (LinkedHashMap<Identifier,Type>)$4.obj;
        Type retType = (Type)$7.obj;
        LinkedList<Stmt> bodylist = new LinkedList<Stmt>();
        bodylist.add(new ExprStmt());
    
        FuncDef newfun = new FuncDef((Identifier)$2.obj, retType, paramlist, bodylist);
    
    	$$ = new ParserVal(newfun);
    }
    | FUNCTION IDENTIFIER OPAREN parameter_list CPAREN RETURNS TYPE COLON body_statement_list END {
        System.err.println("parser: function definition");
        
        Identifier name = (Identifier)$2.obj;
        
        /*
         * Special caution here on the hash implementation used for parameter
         * list.
         * 
         * In BALL, the parameter list of the function behaves like Java. That
         * is, the parameters' order is also important in distinguishing a
         * function. Therefore, the LinkedHashMap class is used instead of the
         * plain HashMap which may not preserve order.
         */
        LinkedHashMap<Identifier,Type> paramlist = (LinkedHashMap<Identifier,Type>)$4.obj;
        Type retType = (Type)$7.obj;
        LinkedList<Stmt> bodylist = (LinkedList<Stmt>)$9.obj;
        
        FuncDef newfun = new FuncDef((Identifier)$2.obj, retType, paramlist, bodylist);
        
        $$ = new ParserVal(newfun);
    }
;

/** SIM_FUNCTION_DEFINITION **/

/*
 * Just like Function definitions, Sim function definitions can only happen in the top level.
 * The only variables they'll get access to are the global team1 and team2 parameters.
 */
sim_function_definition :
    SIMFUNCTION IDENTIFIER IS COLON body_statement_list END {
        System.err.println("parser: simfunction definition");
        
        Identifier name = (Identifier)$2.obj;
        LinkedList<Stmt> bodylist = (LinkedList<Stmt>)$5.obj;
        
        SimFuncDef newfun = new SimFuncDef((Identifier)$2.obj, bodylist);
        
        table.putEntry(name, newfun);
        $$ = new ParserVal(newfun);
    }
;

parameter_list : 
    parameter {
        // keep track of what names have been used, etc.
        // a LinkedHashMap stores entries in insertion order.
        LinkedHashMap<Identifier,Type> paramlist = 
            new LinkedHashMap<Identifier,Type>();
        
        // parameter node returns a pair (Identifier, Type) inside an Object[]
        Object[] param = (Object[]) $1.obj;
        Identifier name = (Identifier)(param[1]);
        Type t = (Type)((Object[])$1.obj)[0];
        
        paramlist.put(name, t);
        $$ = new ParserVal(paramlist);
    }
    | parameter_list COMMA parameter {
        // add to previous parameter list
        LinkedHashMap<Identifier,Type> paramlist = 
            (LinkedHashMap<Identifier,Type>)$1.obj;

        // parse the parameter, just like before
        Object[] param = (Object[]) $3.obj;
        Identifier name = (Identifier)(param[1]);
        Type t = (Type)((Object[])$3.obj)[0];
      
        // simple error checking on paramlist
        if (paramlist.containsKey(name)) {
            System.err.println("parser: error: parameter with same name " + 
                    name + " already present.");
            System.exit(1);
        }
        
        paramlist.put(name, t);
        $$ = new ParserVal(paramlist);
    }
;

parameter : 
    TYPE IDENTIFIER {
        // returns the Type and Identifier objects as a pair
        Object[] param = new Object[2];
        param[0] = (Object)$1.obj;
        param[1] = (Object)$2.obj;
        $$ = new ParserVal(param);
    }
    ;

/**PRINT_STATEMENT**/
print_statement : 
    PRINT expression SEMICOLON {
        $$ = new ParserVal(new PrintStmt((Expr)$2.obj));
    }
;

/**DECLARATION**/
declaration : 
    TYPE variable_declarators SEMICOLON {
		$$ = new ParserVal(new Declaration((Type)$1.obj, (ArrayList<Object[]>)$2.obj));
	}
;

variable_declarators : 
    variable_declarator {
        ArrayList<Object[]> a = new ArrayList<Object[]>();
        a.add((Object[])$1.obj);
        $$ = new ParserVal (a);
    }
    | variable_declarators COMMA variable_declarator {
        ArrayList<Object[]> a = (ArrayList<Object[]>)$1.obj;
        a.add((Object[])$3.obj);
        $$ = new ParserVal (a);
    }
;

variable_declarator : 
    IDENTIFIER {
	    Object[] a = new Object[2];
	    a[0] = $1.obj;
        a[1] = null;
	    $$ = new ParserVal (a);
    }
    | IDENTIFIER EQL expression {
        Object[] a = new Object[2];
        a[0] = $1.obj;
        a[1] = $3.obj;
        $$ = new ParserVal (a);
    }
;

/*
 * This is necessary because the compilation rules for stat declarations are
 * different to variable declarations. In variable declarations, the expression
 * is evaluated, but in stat declarations the expression is encapsulated into
 * a function.
 */
stat_declaration : 
    STAT IDENTIFIER EQL stat_expression SEMICOLON {
        StatDef decl = new StatDef((Identifier)$2.obj, (StatExpr)$4.obj);
        $$ = new ParserVal(decl);
    }
;

/* stats don't have access to the full expression grammar, just a part of it */
stat_expression : 
    stat_mult_expr { 
        $$ = new ParserVal(new StatExpr((StatMult)$1.obj)); 
    }
    | stat_expression PLUS stat_mult_expr {
        $$ = new ParserVal(new StatExpr((StatExpr)$1.obj, (StatMult)$3.obj, StatExpr.Op.PLUS));
    }
    | stat_expression MIN stat_mult_expr {
        $$ = new ParserVal(new StatExpr((StatExpr)$1.obj, (StatMult)$3.obj, StatExpr.Op.MIN));
    }
    ;

stat_mult_expr : 
    stat_atom_expr { 
        $$ = new ParserVal(new StatMult((StatAtom)$1.obj)); 
    }
    | stat_mult_expr MULT stat_atom_expr {
        $$ = new ParserVal(new StatMult((StatMult)$1.obj, (StatAtom)$3.obj, StatMult.Op.MULT));
    }
    | stat_mult_expr DIV stat_atom_expr {
        $$ = new ParserVal(new StatMult((StatMult)$1.obj, (StatAtom)$3.obj, StatMult.Op.DIV));
    }
    | stat_mult_expr MOD stat_atom_expr {
        $$ = new ParserVal(new StatMult((StatMult)$1.obj, (StatAtom)$3.obj, StatMult.Op.MOD));
    }
    ;

stat_atom_expr : 
    IDENTIFIER {
        $$ = new ParserVal(new StatAtom((Identifier)$1.obj));
    }
    | NUMBER {
        $$ = new ParserVal(new StatAtom((NumericConst)$1.obj));
    }
    | OPAREN stat_expression CPAREN {
        $$ = new ParserVal(new StatAtom((StatExpr)$2.obj));
    }
    ;

/*
 * Return statements
 * -----------------
 * Functions need to verify what they return to the program. However, since
 * function bodies themselves are multi-layered structures, the "return"
 * statement might be buried deep somewhere inside a loop within a conditional
 * inside another loop, for example.
 * 
 * TODO: implement multi-layered return checking. In particular, examine return
 *       types of statements that contain statements.
 */
jump_statement : 
    RETURN expression SEMICOLON {
        ReturnStmt newret = new ReturnStmt((Expr)$2.obj);
        $$ = new ParserVal(newret);
    }
;

/**ASSIGNMENT_STATEMENT**/ 
/*
 * A few notes on assignment:
 * - the normal EQL operator, '=', can be used on any type. All other operators
 *   must be a number assignment (for example, 'team x += load("dodgers.team");'
 *   returns an error)
 * - both the variable the identifier is referring to and the expression must
 *   match in type.
 */
assignment_statement :
    // make the node
    IDENTIFIER assignment_operator expression SEMICOLON {
        AssignmentStmt node = 
            new AssignmentStmt((Identifier)$1.obj, 
                    (AssignmentStmt.Op)$2.obj,
                    (Expr)$3.obj);
        $$ = new ParserVal(node);
    }
;

/* operators of assignment */
assignment_operator : 
    EQL { $$ = new ParserVal(AssignmentStmt.Op.EQL); }
    | PLUSEQL { $$ = new ParserVal(AssignmentStmt.Op.PLUSEQL); }
    | MINEQL  { $$ = new ParserVal(AssignmentStmt.Op.MINEQL); }
    | MULTEQL { $$ = new ParserVal(AssignmentStmt.Op.MULTEQL); }
    | DIVEQL  { $$ = new ParserVal(AssignmentStmt.Op.DIVEQL); }
    | MODEQL  { $$ = new ParserVal(AssignmentStmt.Op.MODEQL); }
;

/**ACTIVATE_STATEMENT**/ 
/*
 * Set Simulator.theSimFunction to the simulation function.
 */
activate_statement :
    ACTIVATE IDENTIFIER SEMICOLON {
	ActivateStmt activateNode = new ActivateStmt((Identifier)$2.obj);
	$$ = new ParserVal(activateNode);
    }
;


/**EXPRESSION_STATEMENT**/
/* simply evaluate the expression. Usually wanted for the side effects. */
expression_statement : 
    SEMICOLON {
        ExprStmt stmt = new ExprStmt();
        $$ = new ParserVal(stmt);
    }
    | expression SEMICOLON {
        ExprStmt stmt = new ExprStmt((Expr)$1.obj);
        $$ = new ParserVal(stmt);
    }
;


/* EXPRESSION */
expression : logical_or_expression { $$ = $1; }
;

/* LOGICAL */
logical_or_expression : logical_and_expression
                      | logical_or_expression OR logical_and_expression { 
                    	$$ = new ParserVal(new LogicalExpr(LogicalExpr.Op.OR,(Expr)$1.obj, (Expr)$3.obj));}
;

logical_and_expression : logical_not_expression
                       | logical_and_expression AND logical_not_expression { 
                    	$$ = new ParserVal(new LogicalExpr(LogicalExpr.Op.AND,(Expr)$1.obj, (Expr)$3.obj));}
;

logical_not_expression : comparison_expression
                       | NOT logical_not_expression { 
                    	$$ = new ParserVal(new LogicalExpr(LogicalExpr.Op.NOT,(Expr)$2.obj, null));}
;


/*COMPARISON*/
comparison_expression : addition_expression
                      | comparison_expression IS   addition_expression { 
                    		$$ = new ParserVal(new ComparisonExpr(ComparisonExpr.Op.IS,(Expr)$1.obj, (Expr)$3.obj));}
                      | comparison_expression ISNOT addition_expression { 
                    		$$ = new ParserVal(new ComparisonExpr(ComparisonExpr.Op.ISNOT,(Expr)$1.obj, (Expr)$3.obj));}
                      | comparison_expression GT    addition_expression { 
                    		$$ = new ParserVal(new ComparisonExpr(ComparisonExpr.Op.GT,(Expr)$1.obj, (Expr)$3.obj));}
                      | comparison_expression LT    addition_expression { 
                    		$$ = new ParserVal(new ComparisonExpr(ComparisonExpr.Op.LT,(Expr)$1.obj, (Expr)$3.obj));}
                      | comparison_expression GTE   addition_expression { 
                    		$$ = new ParserVal(new ComparisonExpr(ComparisonExpr.Op.GTE,(Expr)$1.obj, (Expr)$3.obj));}
                      | comparison_expression LTE   addition_expression { 
                    		$$ = new ParserVal(new ComparisonExpr(ComparisonExpr.Op.LTE,(Expr)$1.obj, (Expr)$3.obj));}
;

/* ARITHMETIC */
addition_expression : multiplication_expression
                    | addition_expression PLUS multiplication_expression { 
                    	$$ = new ParserVal(new ArithmeticExpr(ArithmeticExpr.Op.PLUS,(Expr)$1.obj, (Expr)$3.obj));}
                    | addition_expression MIN multiplication_expression {  
                    	$$ = new ParserVal(new ArithmeticExpr(ArithmeticExpr.Op.MIN,(Expr)$1.obj, (Expr)$3.obj));}
;

multiplication_expression : unary_expression
						  | multiplication_expression MULT unary_expression { 
                    			$$ = new ParserVal(new ArithmeticExpr(ArithmeticExpr.Op.MULT,(Expr)$1.obj, (Expr)$3.obj));}
                    	  | multiplication_expression DIV unary_expression { 
                    			$$ = new ParserVal(new ArithmeticExpr(ArithmeticExpr.Op.DIV,(Expr)$1.obj, (Expr)$3.obj));}
                    	  | multiplication_expression MOD unary_expression { 
                    			$$ = new ParserVal(new ArithmeticExpr(ArithmeticExpr.Op.MOD,(Expr)$1.obj, (Expr)$3.obj));}
;

/* UNARY */
unary_expression : postfix_expression { $$ = $1; }
;

/* POSTFIX */
postfix_expression : primary_expression { $$ = $1; }
;

/* PRIMARY */
primary_expression : atom_expression { $$ = $1; }
		   | function_call { $$ = $1; }
;

/* FUNCTION_CALL */
function_call : IDENTIFIER OPAREN CPAREN {
		$$ = new ParserVal(new Funcall((Identifier)$1.obj));
	      }	
              | IDENTIFIER OPAREN argument_list CPAREN {
		$$ = new ParserVal(new Funcall((Identifier)$1.obj, (ArrayList<Expr>)$3.obj));
	      }	
;

argument_list : 
    expression {
        ArrayList<Expr> a = new ArrayList<Expr>();
		a.add((Expr)$1.obj);
		$$ = new ParserVal(a);
    }
    | argument_list COMMA expression {
		ArrayList<Expr> a = (ArrayList<Expr>)$1.obj;
		a.add((Expr)$3.obj);
		$$ = new ParserVal(a);
    }
;


/* ATOM_EXPRESSION */
atom_expression : 
    STRING { 
        System.err.println("got string " + $1.obj); 
        $$ = new ParserVal(new AtomicExpr((StringConst)($1.obj)));
    }
    | IDENTIFIER {
        $$ = new ParserVal(new AtomicExpr((Identifier)($1.obj)));
    }
    | NUMBER {
        $$ = new ParserVal(new AtomicExpr((NumericConst)($1.obj)));
    }
    | OPAREN expression CPAREN {
    	$$ = new ParserVal(new AtomicExpr((Expr)($2.obj)));
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

/*A list of variable declarations */
LinkedList<Declaration> varDeclarations = new LinkedList<Declaration>();

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
            new SymbolTable(true), name);
	yyparser.yyparse();
}

