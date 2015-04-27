# Details #
```

%{
    import java.io.*;
%}

%token identifier
%token STRING
%token SEMICOLON
%token PRINT
%token number


%%
 /***PROGRAM***/
program : statement_list
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
atom_expression : STRING
;

%%

  /* a reference to the lexer object */
  private Yylex lexer;

  /* interface to the lexer */
  private int yylex () {
    int yyl_return = -1;
    try {
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }

  /* error reporting */
  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }
  /* lexer is created in the constructor */
  public parser(Reader r) {
    lexer = new Yylex(r, this);
  }

  /* that's how you use the parser */
  public static void main(String args[]) throws IOException {
    parser yyparser = new parser(new FileReader(args[0]));
    yyparser.yyparse();
  }


```