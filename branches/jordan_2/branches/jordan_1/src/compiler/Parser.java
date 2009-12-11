//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package compiler;



//#line 8 "src/compiler/ball.y"
import java.io.*;
import java.util.*;
import lexer.*;
import codegen.*;

/* necessary because ParserVal deals with generics */
@SuppressWarnings("unchecked")
//#line 25 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short STRING=257;
public final static short IDENTIFIER=258;
public final static short NUMBER=259;
public final static short SEMICOLON=260;
public final static short COLON=261;
public final static short EQL=262;
public final static short PLUSEQL=263;
public final static short MINEQL=264;
public final static short MULTEQL=265;
public final static short DIVEQL=266;
public final static short MODEQL=267;
public final static short COMMA=268;
public final static short OPAREN=269;
public final static short CPAREN=270;
public final static short PRINT=271;
public final static short FUNCTION=272;
public final static short SIMFUNCTION=273;
public final static short RETURN=274;
public final static short RETURNS=275;
public final static short IS=276;
public final static short TYPE=277;
public final static short END=278;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    6,    6,    3,    3,
    3,    3,    3,    4,    4,    5,   12,   12,   13,    9,
    7,   15,   15,   16,   16,   10,   11,   17,   17,   17,
   17,   17,   17,    8,    8,   14,   18,   19,   20,   21,
   22,   23,   24,   25,   26,   26,   28,   28,   29,   29,
   27,   27,   27,
};
final static short yylen[] = {                            2,
    1,    1,    2,    1,    1,    1,    1,    2,    1,    1,
    1,    1,    1,    9,   10,    6,    1,    3,    2,    3,
    3,    1,    3,    1,    3,    3,    4,    1,    1,    1,
    1,    1,    1,    1,    2,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    3,    4,    1,    3,
    1,    1,    1,
};
final static short yydefred[] = {                         0,
   51,    0,   53,   34,    0,    0,    0,    0,    0,    0,
    0,    2,    4,    5,    6,    9,   10,   11,   12,   13,
    0,   36,   37,   38,   39,   40,   41,   42,   43,   44,
   45,   46,   28,   29,   30,   31,   32,   33,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   22,    3,   35,
   47,   49,    0,    0,   20,    0,    0,   26,    0,   21,
    0,    0,   48,   27,    0,    0,   17,    0,   25,   23,
   50,   19,    0,    0,    7,    0,   18,    0,   16,    8,
    0,    0,   14,    0,   15,
};
final static short yydgoto[] = {                         10,
   11,   12,   13,   14,   15,   76,   16,   17,   18,   19,
   20,   66,   67,   21,   47,   48,   40,   22,   23,   24,
   25,   26,   27,   28,   29,   30,   31,   32,   53,
};
final static short yysindex[] = {                      -211,
    0, -191,    0,    0, -226, -257, -256, -226, -250,    0,
 -211,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -247,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -243, -226,
 -249, -238, -192, -197, -210, -179, -223,    0,    0,    0,
    0,    0, -251, -195,    0, -189, -176,    0, -226,    0,
 -250, -226,    0,    0, -172, -188,    0, -190,    0,    0,
    0,    0, -189, -186,    0, -253,    0, -187,    0,    0,
 -170, -248,    0, -219,    0,
};
final static short yyrindex[] = {                         0,
    0, -168,    0,    0,    0,    0,    0,    0,    0,    0,
   93,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -217,    0,    0,    0,    0, -204,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   83,  -40,    0,    0,   13,    0,    0,    0,    0,
    0,    0,   23,   -5,    0,   36,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=97;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         42,
   43,   44,   45,    1,    2,    3,    4,   46,    1,    2,
    3,    4,   50,    1,   41,    3,   62,    5,   63,   39,
    8,   55,    5,    9,   79,    8,   51,   75,    9,   83,
    1,   41,    3,   52,   54,   80,   60,    1,    2,    3,
    4,   75,   52,   80,   61,    1,    2,    3,    4,   58,
   52,    5,   52,   69,    8,   24,   71,    9,   85,    5,
    6,    7,    8,   24,   64,    9,    1,    2,    3,    4,
   33,   34,   35,   36,   37,   38,   56,   39,   57,   73,
    5,   74,   59,    8,   68,   72,    9,   65,   78,   81,
   82,   52,    1,   49,   84,   77,   70,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          5,
  258,  258,    8,  257,  258,  259,  260,  258,  257,  258,
  259,  260,  260,  257,  258,  259,  268,  271,  270,  269,
  274,  260,  271,  277,  278,  274,  270,   68,  277,  278,
  257,  258,  259,   39,   40,   76,  260,  257,  258,  259,
  260,   82,  260,   84,  268,  257,  258,  259,  260,  260,
  268,  271,  270,   59,  274,  260,   62,  277,  278,  271,
  272,  273,  274,  268,  260,  277,  257,  258,  259,  260,
  262,  263,  264,  265,  266,  267,  269,  269,  276,  268,
  271,  270,  262,  274,  261,  258,  277,  277,  275,  277,
  261,  260,    0,   11,   82,   73,   61,
};
}
final static short YYFINAL=10;
final static short YYMAXTOKEN=278;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"STRING","IDENTIFIER","NUMBER","SEMICOLON","COLON","EQL",
"PLUSEQL","MINEQL","MULTEQL","DIVEQL","MODEQL","COMMA","OPAREN","CPAREN",
"PRINT","FUNCTION","SIMFUNCTION","RETURN","RETURNS","IS","TYPE","END",
};
final static String yyrule[] = {
"$accept : program",
"program : statement_list",
"statement_list : statement",
"statement_list : statement_list statement",
"statement : body_statement",
"statement : function_definition",
"statement : sim_function_definition",
"body_statement_list : body_statement",
"body_statement_list : body_statement_list body_statement",
"body_statement : declaration",
"body_statement : expression_statement",
"body_statement : print_statement",
"body_statement : jump_statement",
"body_statement : assignment_statement",
"function_definition : FUNCTION IDENTIFIER OPAREN parameter_list CPAREN RETURNS TYPE COLON END",
"function_definition : FUNCTION IDENTIFIER OPAREN parameter_list CPAREN RETURNS TYPE COLON body_statement_list END",
"sim_function_definition : SIMFUNCTION IDENTIFIER IS COLON body_statement_list END",
"parameter_list : parameter",
"parameter_list : parameter_list COMMA parameter",
"parameter : TYPE IDENTIFIER",
"print_statement : PRINT expression SEMICOLON",
"declaration : TYPE variable_declarators SEMICOLON",
"variable_declarators : variable_declarator",
"variable_declarators : variable_declarators COMMA variable_declarator",
"variable_declarator : IDENTIFIER",
"variable_declarator : IDENTIFIER EQL expression",
"jump_statement : RETURN expression SEMICOLON",
"assignment_statement : IDENTIFIER assignment_operator expression SEMICOLON",
"assignment_operator : EQL",
"assignment_operator : PLUSEQL",
"assignment_operator : MINEQL",
"assignment_operator : MULTEQL",
"assignment_operator : DIVEQL",
"assignment_operator : MODEQL",
"expression_statement : SEMICOLON",
"expression_statement : expression SEMICOLON",
"expression : logical_or_expression",
"logical_or_expression : logical_and_expression",
"logical_and_expression : logical_not_expression",
"logical_not_expression : comparison_expression",
"comparison_expression : addition_expression",
"addition_expression : multiplication_expression",
"multiplication_expression : unary_expression",
"unary_expression : postfix_expression",
"postfix_expression : primary_expression",
"primary_expression : atom_expression",
"primary_expression : function_call",
"function_call : IDENTIFIER OPAREN CPAREN",
"function_call : IDENTIFIER OPAREN argument_list CPAREN",
"argument_list : expression",
"argument_list : argument_list COMMA expression",
"atom_expression : STRING",
"atom_expression : IDENTIFIER",
"atom_expression : NUMBER",
};

//#line 452 "src/compiler/ball.y"

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

//#line 370 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 72 "src/compiler/ball.y"
{ 
        System.err.println("adding node for _program_");
        LinkedList<Stmt> stlist = (LinkedList<Stmt>)val_peek(0).obj;
        Program top = new Program(stlist, outname);
        top.gen(table); /* moves to intermediate code generation stage*/
    }
break;
case 2:
//#line 91 "src/compiler/ball.y"
{ /* reached beginning of statement list*/
        LinkedList<Stmt> newlist = new LinkedList<Stmt>();
        newlist.addLast((Stmt)val_peek(0).obj);
        yyval = new ParserVal(newlist);
    }
break;
case 3:
//#line 96 "src/compiler/ball.y"
{ /* add as we propagate up*/
        LinkedList<Stmt> cur = (LinkedList<Stmt>)val_peek(1).obj;
        cur.addLast((Stmt)val_peek(0).obj);
        yyval = new ParserVal(cur);
    }
break;
case 4:
//#line 114 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 5:
//#line 115 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 116 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 124 "src/compiler/ball.y"
{
        LinkedList<Stmt> newlist = new LinkedList<Stmt>();
        newlist.addLast((Stmt)val_peek(0).obj);
        yyval = new ParserVal(newlist);
    }
break;
case 8:
//#line 129 "src/compiler/ball.y"
{
        LinkedList<Stmt> cur = (LinkedList<Stmt>)val_peek(1).obj;
        cur.addLast((Stmt)val_peek(0).obj);
        yyval = new ParserVal(cur);
    }
break;
case 9:
//#line 142 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 10:
//#line 143 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 144 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 12:
//#line 145 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 13:
//#line 146 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 14:
//#line 157 "src/compiler/ball.y"
{
        System.err.println("parser: function definition");
    
        Identifier name = (Identifier)val_peek(7).obj;
    
        /*
         * Special caution here on the hash implementation used for parameter
         * list.
         * 
         * In BALL, the parameter list of the function behaves like Java. That
         * is, the parameters' order is also important in distinguishing a
         * function. Therefore, the LinkedHashMap class is used instead of the
         * plain HashMap which may not preserve order.
         */
        LinkedHashMap<Identifier,Type> paramlist = (LinkedHashMap<Identifier,Type>)val_peek(5).obj;
        Type retType = (Type)val_peek(2).obj;
        LinkedList<Stmt> bodylist = new LinkedList<Stmt>();
        bodylist.add(new ExprStmt());
    
        FuncDef newfun = new FuncDef((Identifier)val_peek(7).obj, retType, paramlist, bodylist);
    
    	yyval = new ParserVal(newfun);
    }
break;
case 15:
//#line 180 "src/compiler/ball.y"
{
        System.err.println("parser: function definition");
        
        Identifier name = (Identifier)val_peek(8).obj;
        
        /*
         * Special caution here on the hash implementation used for parameter
         * list.
         * 
         * In BALL, the parameter list of the function behaves like Java. That
         * is, the parameters' order is also important in distinguishing a
         * function. Therefore, the LinkedHashMap class is used instead of the
         * plain HashMap which may not preserve order.
         */
        LinkedHashMap<Identifier,Type> paramlist = (LinkedHashMap<Identifier,Type>)val_peek(6).obj;
        Type retType = (Type)val_peek(3).obj;
        LinkedList<Stmt> bodylist = (LinkedList<Stmt>)val_peek(1).obj;
        
        FuncDef newfun = new FuncDef((Identifier)val_peek(8).obj, retType, paramlist, bodylist);
        
        yyval = new ParserVal(newfun);
    }
break;
case 16:
//#line 211 "src/compiler/ball.y"
{
        System.err.println("parser: simfunction definition");
        
        Identifier name = (Identifier)val_peek(4).obj;
        LinkedList<Stmt> bodylist = (LinkedList<Stmt>)val_peek(1).obj;
        
        SimFuncDef newfun = new SimFuncDef((Identifier)val_peek(4).obj, bodylist);
        
        table.putEntry(name, newfun);
        yyval = new ParserVal(newfun);
    }
break;
case 17:
//#line 225 "src/compiler/ball.y"
{
        /* keep track of what names have been used, etc.*/
        /* a LinkedHashMap stores entries in insertion order.*/
        LinkedHashMap<Identifier,Type> paramlist = 
            new LinkedHashMap<Identifier,Type>();
        
        /* parameter node returns a pair (Identifier, Type) inside an Object[]*/
        Object[] param = (Object[]) val_peek(0).obj;
        Identifier name = (Identifier)(param[1]);
        Type t = (Type)((Object[])val_peek(0).obj)[0];
        
        paramlist.put(name, t);
        yyval = new ParserVal(paramlist);
    }
break;
case 18:
//#line 239 "src/compiler/ball.y"
{
        /* add to previous parameter list*/
        LinkedHashMap<Identifier,Type> paramlist = 
            (LinkedHashMap<Identifier,Type>)val_peek(2).obj;

        /* parse the parameter, just like before*/
        Object[] param = (Object[]) val_peek(0).obj;
        Identifier name = (Identifier)(param[1]);
        Type t = (Type)((Object[])val_peek(0).obj)[0];
      
        /* simple error checking on paramlist*/
        if (paramlist.containsKey(name)) {
            System.err.println("parser: error: parameter with same name " + 
                    name + " already present.");
            System.exit(1);
        }
        
        paramlist.put(name, t);
        yyval = new ParserVal(paramlist);
    }
break;
case 19:
//#line 262 "src/compiler/ball.y"
{
        /* returns the Type and Identifier objects as a pair*/
        Object[] param = new Object[2];
        param[0] = (Object)val_peek(1).obj;
        param[1] = (Object)val_peek(0).obj;
        yyval = new ParserVal(param);
    }
break;
case 20:
//#line 273 "src/compiler/ball.y"
{
        yyval = new ParserVal(new PrintStmt((Expr)val_peek(1).obj));
    }
break;
case 21:
//#line 280 "src/compiler/ball.y"
{
		yyval = new ParserVal(new Declaration((Type)val_peek(2).obj, (ArrayList<Object[]>)val_peek(1).obj));
	}
break;
case 22:
//#line 286 "src/compiler/ball.y"
{
        ArrayList<Object[]> a = new ArrayList<Object[]>();
        a.add((Object[])val_peek(0).obj);
        yyval = new ParserVal (a);
    }
break;
case 23:
//#line 291 "src/compiler/ball.y"
{
        ArrayList<Object[]> a = (ArrayList<Object[]>)val_peek(2).obj;
        a.add((Object[])val_peek(0).obj);
        yyval = new ParserVal (a);
    }
break;
case 24:
//#line 299 "src/compiler/ball.y"
{
	    Object[] a = new Object[2];
	    a[0] = val_peek(0).obj;
        a[1] = null;
	    yyval = new ParserVal (a);
    }
break;
case 25:
//#line 305 "src/compiler/ball.y"
{
        Object[] a = new Object[2];
        a[0] = val_peek(2).obj;
        a[1] = val_peek(0).obj;
        yyval = new ParserVal (a);
    }
break;
case 26:
//#line 325 "src/compiler/ball.y"
{
        ReturnStmt newret = new ReturnStmt((Expr)val_peek(1).obj);
        yyval = new ParserVal(newret);
    }
break;
case 27:
//#line 342 "src/compiler/ball.y"
{
        AssignmentStmt node = 
            new AssignmentStmt((Identifier)val_peek(3).obj, 
                    (AssignmentStmt.Op)val_peek(2).obj,
                    (Expr)val_peek(1).obj);
        yyval = new ParserVal(node);
    }
break;
case 28:
//#line 353 "src/compiler/ball.y"
{ yyval = new ParserVal(AssignmentStmt.Op.EQL); }
break;
case 29:
//#line 354 "src/compiler/ball.y"
{ yyval = new ParserVal(AssignmentStmt.Op.PLUSEQL); }
break;
case 30:
//#line 355 "src/compiler/ball.y"
{ yyval = new ParserVal(AssignmentStmt.Op.MINEQL); }
break;
case 31:
//#line 356 "src/compiler/ball.y"
{ yyval = new ParserVal(AssignmentStmt.Op.MULTEQL); }
break;
case 32:
//#line 357 "src/compiler/ball.y"
{ yyval = new ParserVal(AssignmentStmt.Op.DIVEQL); }
break;
case 33:
//#line 358 "src/compiler/ball.y"
{ yyval = new ParserVal(AssignmentStmt.Op.MODEQL); }
break;
case 34:
//#line 365 "src/compiler/ball.y"
{
        ExprStmt stmt = new ExprStmt();
        yyval = new ParserVal(stmt);
    }
break;
case 35:
//#line 369 "src/compiler/ball.y"
{
        ExprStmt stmt = new ExprStmt((Expr)val_peek(1).obj);
        yyval = new ParserVal(stmt);
    }
break;
case 36:
//#line 377 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 37:
//#line 381 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 38:
//#line 384 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 39:
//#line 387 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 40:
//#line 391 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 41:
//#line 395 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 42:
//#line 398 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 43:
//#line 402 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 44:
//#line 406 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 45:
//#line 410 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 46:
//#line 411 "src/compiler/ball.y"
{ yyval = val_peek(0); }
break;
case 47:
//#line 415 "src/compiler/ball.y"
{
		yyval = new ParserVal(new Funcall((Identifier)val_peek(2).obj));
	      }
break;
case 48:
//#line 418 "src/compiler/ball.y"
{
		yyval = new ParserVal(new Funcall((Identifier)val_peek(3).obj, (ArrayList<Expr>)val_peek(1).obj));
	      }
break;
case 49:
//#line 424 "src/compiler/ball.y"
{
        ArrayList<Expr> a = new ArrayList<Expr>();
		a.add((Expr)val_peek(0).obj);
		yyval = new ParserVal(a);
    }
break;
case 50:
//#line 429 "src/compiler/ball.y"
{
		ArrayList<Expr> a = (ArrayList<Expr>)val_peek(2).obj;
		a.add((Expr)val_peek(0).obj);
		yyval = new ParserVal(a);
    }
break;
case 51:
//#line 439 "src/compiler/ball.y"
{ 
        System.err.println("got string " + val_peek(0).obj); 
        yyval = new ParserVal(new Expr((StringConst)(val_peek(0).obj)));
    }
break;
case 52:
//#line 443 "src/compiler/ball.y"
{
        yyval = new ParserVal(new Expr((Identifier)(val_peek(0).obj)));
    }
break;
case 53:
//#line 446 "src/compiler/ball.y"
{
        yyval = new ParserVal(new Expr((NumericConst)(val_peek(0).obj)));
    }
break;
//#line 899 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
