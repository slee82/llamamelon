
Parser : ball.lex ball.y
	jflex ball.lex
	byaccj -J ball.y
	javac Parser.java

.PHONY : clean
clean:
	rm -f *.java~ Yylex.java Parser.java ParserVal.java *.class
