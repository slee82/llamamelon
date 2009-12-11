package codegen;

import lexer.*;

public class Expr extends ParseTreeNode {

    public Expr(StringConst const1) {
        this.stringval = const1.val;
    }
    
    public void gen() {
        // later needs to differ by type
        System.out.println(stringval);
    }

    private int type;
    private String stringval;
}
