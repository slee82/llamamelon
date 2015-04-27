# Introduction #

lex specification


# Details #
```
%%

%byaccj

%{
  /* store a reference to the parser object */
  private parser yyparser;

  /* constructor taking an additional parser object */
  public Yylex(java.io.Reader r, parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

%%

";" { return SEMICOLON; }
print   { return PRINT; }
\"([^\"\\]|\\.)*\" { return yytext(); }

```