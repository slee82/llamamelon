/*
 * COMS W4119 PROGRAMMING LANGUAGES AND TRANSLATORS FALL 2009
 * Team llamamelon - BALL language
 * ball.lex - Lexer specification of the BALL language (using JFlex)
 */

%%

%byaccj

/* 
 * =========================================================
 *  This section goes straight into Yylex.java untranslated
 * =========================================================
 */

%{ // open escape section

    /* store a reference to the parser object */
    private Parser yyparser;

    /* store a reference to the symbol table */
    private SymbolTable table;

    /* 
     * constructor initializes both the parser and table references.
     */
    public Yylex(java.io.Reader r, Parser yyparser, SymbolTable table) {
        this(r);
        this.yyparser = yyparser;
        this.table = table;
    }

%} // close escape section

/* 
 * =====================
 *  Regular Expressions
 * =====================
 */

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* Comments */
Comment                 = {TraditionalComment}	
                        | {EndOfLineComment} 
                        | {DocumentationComment}

TraditionalComment      = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment        = "//" {InputCharacter}* {LineTerminator}
DocumentationComment    = "/**" {CommentContent} "*"+ "/"
CommentContent          = ( [^*] | \*+ [^/*] )*

/* Constants */
StringConst	            = \"([^\"\\]|\\.)*\"

/* Identifiers */
Identifier              = [:jletterdigit:]*[:jletter:][:jletterdigit:]*

%%

/* 
 * ======================
 *  Lexer (Syntax) Rules
 * ======================
 */

";"             { return Parser.SEMICOLON; }

print           { /* got a print statement, add to sym. tbl. and notify parser */
                    // TODO: add symbol table addition code here
                    System.out.println("lexer: found 'print'");
                    return Parser.PRINT; // TODO: couple return with table reference
                }

{StringConst}	{ /* got a string, add to sym. tbl. and notify parser */
                    // TODO: add symbol table addition code here
                    yyparser.yylval = new ParserVal(yytext());
                    return Parser.STRING; // TODO: couple return with table reference
                }

//{Identifier}	{ return Parser.IDENTIFIER; }

{Comment} 		{ /* ignore */ }
{WhiteSpace}	{ /* ignore */ }
