# Program #

hello.ball
```
print "Playball!";
```

Run like this:
```
$ ball hello.ball
Playball!
$
```

# Grammar #

#summary The full grammar for our language
_Refer to Appendix A13 of K&R. Also check http://java.sun.com/docs/books/jls/second_edition/html/syntax.doc.html_



This is the yacc-compatible grammar for our language:

The grammar has the undefined terminal symbols: _identifier, string, number_.

```

%token identifier
%token string
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
print_statement : "print" expression ";"
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
atom_expression : string
                ;

%%
```