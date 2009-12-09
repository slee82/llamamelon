/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import java.util.*;

import lexer.Identifier;

import org.junit.Test;

/**
 * @author cipta
 *
 */
public class IdentifierTest {

    /**
     * Test method for {@link lexer.Identifier#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        Identifier a = new Identifier("abc");
        Identifier b = new Identifier("def");
        Identifier c = new Identifier("abc");
        /*
        assertTrue(a.equals(c));
        assertTrue(a.equals((Object)c));
        assertTrue(c.equals(c));
        assertTrue(c.equals(a));
        assertTrue(a.equals(a));
        assertFalse(a.equals(b));
        assertFalse(c.equals(b));
        assertFalse(b.equals(a));
        */
    }

    /**
     * Test method for {@link lexer.Identifier#Identifier(java.lang.String)}.
     */
    @Test
    public void testIdentifier() {
        
        HashMap<Identifier, Integer> test2 = new HashMap<Identifier, Integer>();
        test2.put(new Identifier("myfun"), new Integer(3));
        test2.put(new Identifier("myfun1"), new Integer(4));
        test2.put(new Identifier("myfun2"), new Integer(5));
        assertNotNull(test2.get(new Identifier("myfun")));
        assertNotNull(test2.get(new Identifier("myfun2")));
        assertNotNull(test2.get(new Identifier("myfun1")));
    }
    
    /**
     * Test method for {@link lexer.Identifier#hashcode(java.lang.String)}.
     */
    @Test
    public void testHashCode() {
        System.out.println((new Identifier("myfun")).hashCode());
        Identifier a = new Identifier("abc");
        Identifier c = new Identifier("abc");
        assertEquals(a.hashCode(), c.hashCode());
        assertEquals(c.hashCode(), a.hashCode());
    }

    /**
     * Test method for {@link lexer.Identifier#getID()}.
     */
    @Test
    public void testGetID() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link lexer.Identifier#setID(java.lang.String)}.
     */
    @Test
    public void testSetID() {
        fail("Not yet implemented");
    }

}
