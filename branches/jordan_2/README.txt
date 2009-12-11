From: Cipta
To: Llamamelon

All right, since there's a lot I changed during these past few days I'm writing
this file so you guys can get used to the chages I made faster. Much of the
compiler is adapted from the Dragon Book's Appendix A, I suggest you guys skim
through it.

Where are the source files?
---------------------------
Our program consists of 16 java files now, most of them small files that contain
small fragments of the java code for a given nonterminal like print statements
or the program. The source is located in ./src, divided to 4 packages.

1. codegen  		= this package contains classes that correspond with the grammar
              		 nonterminals found in our program. All of them are subclasses of
              		 ParseTreeNode.java. Each has a method gen() that will print its 
              		 java code to stdout (for now).
             
2. compiler 		= this is where the compiler, that is the lexer and parser, are
             		 defined The intermediate code generator is just the gen()
              		 method for now, it might change when we add more stuff. The symbol
             		 table is defined here as well.
              
3. lexer    		= this package contains classes for lexical analysis. Things like
              		 what a token is, a keyword, string constants, etc. are defined
              		 here.

4. javabackend		= this package contains all of the supporting java files for ball.
			 (for example, built-in functions and classes).
              
Compiling and running the program
---------------------------------
Compiling isn't done with a general makefile now. Since I divided the source to
packages and all that, I'm using Ant to build the project.

http://en.wikipedia.org/wiki/Apache_Ant

An Ant build file is basically a makefile in XML. At the top you have the
project name and the default target. Then you have "properties", basically 
makefile variables like CFLAGS or LDLIBS from Advanced Programming.

To compile the program, just type "ant compile" in the terminal. There should be
an ant program wherever you have Java, I know cunix and clic has it. All the
class files are made in a new directory called "build". To run the program,
go to the build directory and enter "java compiler.Parser <source>" instead
of going to the "compiler" directory.

To clean everything, go to the top level and just enter "ant clean". The "build"
directory, Parser.java, ParserVal.java, Yylex.java and all the class files will
be deleted.

How it works
============
Suppose we run the compiler on a file:

$ java compiler.Parser ../../src/play.ball

The compiler then determines the name of the java class that's going
to be made by deleting everything before the last '/' and everything after the
first '.', which in the above case leaves us with "play".

Then, it constructs the parser and symbol table and supplies it with the name of
the program. Inside the Parser constructor (ball.y:152), the parser creates a
lexer, passes the symbol table to the lexer and saves a reference to the lexer.
In the lexer constructor (ball.lex:33) a reference to the calling Parser and
the symbol table is stored. After this process, the two-way link between Parser
and Yylex (lexer) is established.

Communication between lexer and parser
--------------------------------------
when the Yylex object detects a complete token, it runs the code segment found
in the block section next to the token. When the lexer wants to send something
bigger than a notification that it got a token (for example, when detecting a 
string constant and needs to send it to the parser), the lexer wraps the value
inside a ParserVal class and puts it in "yyparser.yylval", a package private
field inside the Parser.

At the Parser end, whenever the string constant is referenced in the grammar, 
the ParserVal is extracted with the $0, $1, $2 shortcut. For example, in the
rule 

A : B C D;

if B is some kind of terminal representing something taken from the lexer
(a string constant for example), the object stored in B can be extracted with
"$1", as a ParserVal object. A ParserVal object in turn holds several fields
like a numeric value, object value (used the most), String value, etc. Whatever
the lexer stores in "yyparser.yylval" is referenced as $X in the parser. $$, in
turn, is "yyparser.yylval" for the rule head.

