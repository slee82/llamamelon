
%%

%byaccj

%{
  /* store a reference to the parser object */
  private Parser yyparser;

  /* constructor taking an additional parser object */
  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

Identifier = [:jletterdigit:]*[:jletter:][:jletterdigit:]*

%%

";"	{ return Parser.SEMICOLON; }
print	{ 
	System.out.println("lexer: found 'print'");
	return Parser.PRINT; 
	}
\"([^\"\\]|\\.)*\" { yyparser.yylval = new ParserVal(yytext());
                     return Parser.STRING; }

//{Identifier}                   { return Parser.IDENTIFIER; }

{Comment} { /* ignore */ }
{WhiteSpace} { /* ignore */ }