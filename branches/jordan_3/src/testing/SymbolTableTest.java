package testing;

import static org.junit.Assert.*;
import lexer.Identifier;

import org.junit.Test;

import compiler.SymbolTable;

public class SymbolTableTest {

    @Test
    public void testPutEntry() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetEntry() {
        SymbolTable test1 = new SymbolTable(true);
        test1.putEntry(new Identifier("myfun"), new Integer(3));
        test1.putEntry(new Identifier("myfun1"), new Integer(4));
        test1.putEntry(new Identifier("myfun2"), new Integer(5));

        
        assertNotNull(test1.getEntry(new Identifier("myfun")));
        assertNotNull(test1.getEntry(new Identifier("myfun2")));
        assertNotNull(test1.getEntry(new Identifier("myfun1")));
    }

    @Test
    public void testAvailable() {
        fail("Not yet implemented");
    }

}
