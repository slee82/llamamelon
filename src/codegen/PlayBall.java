/* ¯\(°_o)/¯ WHAAT */

package codegen;
import compiler.SymbolTable;

public class PlayBall extends Stmt {
    
    public PlayBall() {
	
    }

    public int a = 9;
    public int INNINGS = 0;

    public String stmtCode(SymbolTable table) {
	for(int i=0; i<3; i++){
	    uhOh();
	}
	diamond();
	for(int i=0; i<3; i++){
	    uhOh();
	}
	diamond();
	for(int i=0; i<3; i++){
	    uhOh();
	}
	diamond();
	for(int i=0; i<3; i++){
	    uhOh();
	}
	diamond();
	for(int i=0; i<3; i++){
	    uhOh();
	}
	diamond();
	for(int i=0; i<3; i++){
	    uhOh();
	}
	diamond();
	for(int i=0; i<3; i++){
	    uhOh();
	}
	diamond();
	for(int i=0; i<3; i++){
	    uhOh();
	}
	diamond();
	for(int i=0; i<3; i++){
	    uhOh();
	}
	diamond();
        return table.indent() + "//WHAAAAAAAAAAT?";
    }

    public void uhOh(){
	String ESC = "\033[";
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame1+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame2+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame3+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame4+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame5+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame6+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame7+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame8+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame9+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame10+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame11+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame12+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame13+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame14+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame15+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame16+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame17+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame18+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame19+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);
	
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame20+"\n**YOUR PROGRAM WILL COMPILE AFTER " + a + " INNINGS**");
	manySec(.1);

	INNINGS++;
	if(INNINGS == 3){
	    INNINGS = 0;
	    a--;
	}
    }

    public void diamond(){
	String ESC = "\033[";
	System.err.print(ESC + "2J"); System.err.flush();
	System.err.println(frame1);
	System.err.println("\n               __--__\n          __---      ---__\n         /                \\\n        .                  .\n       /       Daniel       \\\n      /        Lasry         \\\n     .                        .\n    /                          \\\n   .                            .\n  /   Cipta               Sam    \\\n .   Herwana              Lee     .\n/                                  \\\n\\                /\\                /\n \\              /\\/\\              /\n  \\            /    \\            /\n   \\          /      \\          /\n    \\        /        \\        /\n     \\      /  Nathan  \\      /\n      \\    /   Miller   \\    /\n       \\  /              \\  /\n        \\/\\              /\\/\n         \\/              \\/\n          \\              /\n           \\            /\n            \\  Jordan  /     Team\n             \\ Schau  /   Llamamelon\n              \\      /\n               \\    /\n                \\__/\n                 \\/");
	manySec(2);
    }

    public static void manySec(double s) {
	long hi = (long)(s*1000);
     try {
       Thread.currentThread().sleep(hi);
       }
     catch (InterruptedException e) {
       e.printStackTrace();
       }
     }

    public String frame1 = "\n         /\n        /\n      <<O                                              O\n        |                                            o/|>\n       /|                                              |\n      / |                                              |\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame2 = "\n        /\n       /\n     <<O                                              O\n       \\                                             o/\\ \n       /\\                                              /\n      / /                                              \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame3 = "        /\n       /                                              o\n     <<O                                             _O>\n       \\                                              _\\ \n       /\\                                            | /\n      / /                                              \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

   public String frame4 = "        /\n       /                                              o\n     <<O                                             <O__\n       \\                                              _\\ \n       /\\                                            / /\n      / /                                              \\\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

   public String frame5 = "        /\n       /                                           o \n     <<O                                            \\O__\n       \\                                              \\ \n       /\\                                             /\n      / /                                            | \\\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

   public String frame6 = "        /\n       /                                           \n     <<O                                        o   _O\n       \\                                             /\\ \n       /\\                                             /|\n      / /                                            | \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame7 = "        /\n       /                                           \n     <<O                                o   .   .  _O\n       \\                                            /\\ \n       /\\                                            /\\__\n      / /                                            |\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame8 = "    \\n     \\                                           \n     <<O                     o     .    .   .   . \n       \\                                         __O__ \n       /\\                                          / /\\/\n      / /                                            |\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame9 = "     \n                                                 \n  ___,_O                     .     .    .   .   .   O\n       \\      o   '    '                            /\\ \n       |\\                                            /\\\n      /  \\                                           | \\\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame10 = "\n                                                 \n       O                     .     .    .   .   .   O_\n       \\____o    '    '                            /\\ \n       |\\                                            /|\n      /  \\                                           | \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame11 = "                         .   '   '  o    \n           /         .               \n          /       .  \n       O>>     .                                      O\n       \\                                             /|\\\n       |\\                                            /|\n      /  \\                                           || \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame12 = "                         .   '   '   '  .    \n                     .                     '  o\n                  .                             \n       O       .                                     _O/\n      /|\\                                             |\n       |\\|                                           //\n      / ||                                           \\ \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame13 = "                         .   '   '   '  .    \n                     .                     '  .\n                  .                              '  .  o\n       O       .                                     _O/\n      /|\\                                             |\n       |\\|                                           //\n       |/|                                           \\ \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

   public String frame14 = "                     .                     '  .\n                  .                              '  .  o\n       O       .                                     _O/\n      /|\\                                             |\n       |\\|                                           //\n       |/|                                           \\ \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

   public String frame15 = "\n                         .   '   '   '  .    \n                     .                     '  .\n                                                 '  .\n       O                                            _O_o\n      /|\\                                             \n     | |\\                                            //\n     | |/                                            \\ \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

   public String frame16 = "\n                                 '   '  .    \n                                           '  .\n                                                 '  .\n       O                                             _O\n      /|>                                             |\\o\n     / |\\                                            /|\n    /  |/                                            \\| \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame17 = "\n                                              .\n                                                 '  .\n       O                                              O\n      /|>                                            /|\\o\n     / |\\                                            /|\n    /  |/                                            \\| \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame18 = "\n\n\n      _O                                              O\n     / |>                                            /|\\o\n    / /|                                             /|\n     / |                                             \\| \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame19 = "        /\n       /\n     <<O                                              O\n       |                                            o/|>\n      /|                                             /|\n     / |                                             \\| \n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";

    public String frame20 = "         /\n        /\n      <<O                                              O\n        |                                            o/|>\n       /|                                              |\n      / |                                              |\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";


}
