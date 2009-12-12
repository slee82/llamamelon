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

statement : function_definition
          | sim_function_definition
          | body_statement
          ;

body_statement_list : body_statement
                    | body_statement_list body_statement
                    ;

/*Body Statements are all statements except function declarations*/
body_statement : if_statement
               | iteration_statement
               | jump_statement
               | declaration
               | stat_declaration
               | expression_statement
               | activate_statement
               | print_statement
               | assignment_statement
               ;

/**FUNCTION_DEFINITION**/
function_definition : "function" identifier "(" parameter_list ")" "returns" type ":" body_statement_list "end"
                    ;

parameter_list : parameter
               | parameter_list "," parameter
               ;

parameter : type identifier
          ;

/**SIM_FUNCTION_DEFINITION**/
sim_function_definition : "simfunction" identifier "is:" body_statement_list "end"
                        ;

/**ACTIVATE_STATEMENT**/
activate_statement : "activate" identifier ";"
                   ;

/**PRINT_STATEMENT**/
print_statement : "print" expression ";"
                ;

/**IF_STATEMENT**/
if_statement : "if" "(" expression ")" "then:" body_statement_list "end"
             | "if" "(" expression ")" "then:" body_statement_list else_statement "end"
             ;

else_statement : "else:" body_statement_list
               ;

/**ITERATION_STATEMENT**/
iteration_statement : "do:" body_statement_list "end"
                    | "do" expression "times:" body_statement_list "end"
                    | "foreach" identifier "in" identifier ":" body_statement_list "end"
                    ;

/**JUMP_STATEMENT**/
jump_statement : "stopdo" ";"
               | "return" expression ";"
               | "return" ";"
               ;
              
/**ASSIGNMENT_STATEMENT**/ 
assignment_statement : identifier assignment_operator expression ";"
                     ;

assignment_operator : "=" | "+=" | "-=" | "*=" | "/=" | "%=" ;

/**DECLARATION**/
declaration : type variable_declarators ";"
            ;

variable_declarators : variable_declarator
                     | variable_declarators "," variable_declarator
                     ;

variable_declarator : identifier
                    | identifier "=" expression
                    ;

/*
 * This is necessary because the compilation rules for stat declarations are
 * different to variable declarations. In variable declarations, the expression
 * is evaluated, but in stat declarations the expression is encapsulated into
 * a function.
 */
stat_declaration : "stat" identifier "=" stat_expression ";"
                 ;

stat_expression : stat_mult_expr
                | stat_expression "+" stat_mult_expr
                | stat_expression "-" stat_mult_expr
                ;

stat_mult_expr : stat_atom_expr
               | stat_mult_expr "*" stat_atom_expr
               | stat_mult_expr "/" stat_atom_expr
               | stat_mult_expr "%" stat_atom_expr
               ;

stat_atom_expr : identifier
               | number
               | "(" stat_expression ")"
               ;

/*TYPE*/
type : "number"
     | "string"
     | "list"
     | "team"
     | "player"
     //| "stat"
     | "nothing"
     ;


/**EXPRESSION_STATEMENT**/
expression_statement : ";"
                     | expression ";"
                     ;

/*EXPRESSION*/
expression : logical_or_expression
           ;

/*LOGICAL*/
logical_or_expression : logical_and_expression
                      | logical_or_expression "or" logical_and_expression
                      ;

logical_and_expression : logical_not_expression
                       | logical_and_expression "and" logical_not_expression
                       ;

logical_not_expression : comparison_expression
                       | "not" logical_not_expression
                       ;

/*COMPARISON*/
comparison_expression : addition_expression
                      | comparison_expression "is"   addition_expression
                      | comparison_expression "isnot" addition_expression
                      | comparison_expression ">"    addition_expression
                      | comparison_expression "<"    addition_expression
                      | comparison_expression ">="   addition_expression
                      | comparison_expression "<="   addition_expression
                      ;

/*ARITHMETIC*/
addition_expression : multiplication_expression
                    | addition_expression "+" multiplication_expression
                    | addition_expression "-" multiplication_expression
                    ;

multiplication_expression : unary_expression
                          | multiplication_expression "*" unary_expression
                          | multiplication_expression "/" unary_expression
                          | multiplication_expression "%" unary_expression
                          ;

/*UNARY*/
unary_expression : postfix_expression
                 | "++" unary_expression
                 | "--" unary_expression
                 | primary_expression "from" unary_expression
                 | "any" unary_expression
                 ;

/*POSTFIX*/
postfix_expression : primary_expression
                   | postfix_expression "'s" identifier // attribute/stats call
                   | postfix_expression "where" "(" expression ")"
                   | postfix_expression "++"
                   | postfix_expression "--"
                   ;

/*PRIMARY*/
primary_expression : atom_expression
                   | function_call
                   ;

/*FUNCTION_CALL*/
function_call : identifier "(" ")"
              | identifier "(" argument_list ")"
              ;

argument_list : expression
              | argument_list "," expression
              ;

/*ATOM_EXPRESSION*/
atom_expression : identifier
                | number
                | string
                | list_initializer
                | "nothing"
                | "(" expression ")" // completes the cycle
                ;

list_initializer : "[" variable_list "]"
                 | "[" "]"
                 ;

variable_list : expression
              | variable_list "," expression
              ;
%%