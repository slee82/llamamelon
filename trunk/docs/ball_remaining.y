/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * ball_remaining.y - what needs to be done
 */

%token identifier
%token string
%token number

// ball.y + ball_remaining.y = ball_full.y

%%

/*Body Statements are all statements except function declarations*/
body_statement : if_statement
               | iteration_statement
               | jump_statement
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
               | "return" ";"
               ;

/*UNARY*/
unary_expression : "++" unary_expression
                 | "--" unary_expression
                 ;

/*POSTFIX*/
postfix_expression : postfix_expression "'s" identifier // attribute/stats call
                   | postfix_expression "++"
                   | postfix_expression "--"
                   ;

%%