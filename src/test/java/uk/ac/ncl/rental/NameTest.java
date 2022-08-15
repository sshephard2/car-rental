package uk.ac.ncl.rental;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * NameTest class.  JUnit test cases for the Name class.
 *
 * @author Stephen Shephard
 * @version 1.0
 */
class NameTest {

    /**
     * Test method for Name Constructor.
     */
    @Test
    void testName() {
        Name n = new Name("John", "Smith");
        // An object should have been created
        assertNotNull(n);
    }

    /**
     * Test method for Name Constructor.
     * Null first name given.
     */
    @Test
    void testNameMissingFirstName() {
        assertThrows(IllegalArgumentException.class, () -> new Name(null, "Smith"));
    }

    /**
     * Test method for Name Constructor.
     * Invalid first name given.
     */
    @ParameterizedTest
    @ValueSource(strings = {"john", "JOHN", "jOhn", "j1"})
    void testNameInvalidFirstName(String firstName) {
        assertThrows(IllegalArgumentException.class, () -> new Name(firstName, "Smith"));
    }

    /**
     * Test method for Name Constructor.
     * Null last name given.
     */
    @Test
    void testNameMissingLastName() {
        assertThrows(IllegalArgumentException.class, () -> new Name("John", null));
    }

    /**
     * Test method for Name Constructor.
     * Invalid last name given.
     */
    @ParameterizedTest
    @ValueSource(strings = {"SMith", "smith", "SMITH", "sm1th"})
    void testNameInvalidLastName(String lastname) {
        assertThrows(IllegalArgumentException.class, () -> new Name("J", lastname));
    }

    /**
     * Test method for Name.getFirstName method.
     */
    @Test
    void testGetFirstName() {
        Name n = new Name("John", "Smith");
        assertEquals("John", n.getFirstName());
    }

    /**
     * Test method for Name.getLastName method.
     */
    @Test
    void testGetLastName() {
        Name n = new Name("John", "Smith");
        assertEquals("Smith", n.getLastName());
    }

    /**
     * Test method for Name.equals method.
     */
    @SuppressWarnings("squid:S5785") // testing the custom equals method
    @Test
    void testEqualsObject() {
        Name n1 = new Name("John", "Smith");
        Name n2 = new Name("John", "Smith");
        Name n3 = new Name("John", "Smith");

        // reflexive
        assertTrue(n1.equals(n1));

        // symmetric
        assertTrue(n1.equals(n2));
        assertTrue(n2.equals(n1));

        // transitive
        assertTrue(n2.equals(n3));
        assertTrue(n1.equals(n3));

        // null test
        assertFalse(n1.equals(null));

        // consistent
        for (int i = 0; i < 100; i++) {
            assertTrue(n1.equals(n2));
        }

        // not equal if first names not equal
        assertNotEquals(n1, new Name("John", "Jones"));

        // not equal if last names not equal
        assertNotEquals(n1, new Name("Sarah", "Smith"));
    }

    /**
     * Test method for Name.hashCode method.
     * Tests that when two Name objects are equal, so are their hashcodes.
     */
    @Test
    void testHashCode() {
        Name n1 = new Name("John", "Smith");
        Name n2 = new Name("John", "Smith");

        assertEquals(n1.hashCode(), n2.hashCode());
    }

    /**
     * Test method for Name.toString method.
     */
    @Test
    void testToString() {
        Name n = new Name("John", "Smith");

        assertEquals("John Smith", n.toString());
    }

    /**
     * Test method for valid Name.valueOf method.
     */
    @Test
    void testNameValueOf() {
        Name n = Name.valueOf("John Smith");
        // An object should have been created
        assertNotNull(n);
    }

    /**
     * Test method for null Name.valueOf method.
     */
    @Test
    void testNameNullValueOf() {
        assertThrows(IllegalArgumentException.class, () -> Name.valueOf(null));
    }

    /**
     * Test method for invalid Name.valueOf method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"John Q Smith", "Smith", "", "JOHN smith"})
    void testNameInvalidValueOf(String fullname) {
        assertThrows(IllegalArgumentException.class, () -> Name.valueOf(fullname));
    }

}
