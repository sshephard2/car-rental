package uk.ac.ncl.rental;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.rental.Name;

/**
 * NameTest class.  JUnit test cases for the Name class.
 * @author Stephen Shephard
 * @version 1.0
 *
 */
public class NameTest {

	/**
	 * Test method for Name Constructor.
	 */
	@Test
	public void testName() {
		Name n = new Name("John", "Smith");
		// An object should have been created
		assertNotNull(n);
	}
	
	/**
	 * Test method for Name Constructor.
	 * Null first name given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNameMissingFirstName() {
		new Name(null, "Smith");
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for Name Constructor.
	 * Invalid first name given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNameInvalidFirstName() {
		new Name("john", "Smith");
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for Name Constructor.
	 * Null last name given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNameMissingLastName() {
		new Name("John", null);
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for Name Constructor.
	 * Invalid last name given.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNameInvalidLastName() {
		new Name("J", "S1");
		// No assertion, test method should throw expected Exception type
	}

	/**
	 * Test method for Name.getFirstName method.
	 */
	@Test
	public void testGetFirstName() {
		Name n = new Name("John", "Smith");
		assertEquals("John", n.getFirstName());
	}

	/**
	 * Test method for Name.getLastName method.
	 */
	@Test
	public void testGetLastName() {
		Name n = new Name("John", "Smith");
		assertEquals("Smith", n.getLastName());
	}

	/**
	 * Test method for Name.equals method.
	 */
	@Test
	public void testEqualsObject() {
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
		for (int i=0; i<100; i++) {
			assertTrue(n1.equals(n2));
		}
	}
	
	/**
	 * Test method for Name.hashCode method.
	 * Tests that when two Name objects are equal, so are their hashcodes.
	 */
	@Test
	public void testHashCode() {
		Name n1 = new Name("John", "Smith");
		Name n2 = new Name("John", "Smith");
		
		assertEquals(n1.hashCode(), n2.hashCode());
	}

	/**
	 * Test method for Name.toString method.
	 */
	@Test
	public void testToString() {
		Name n = new Name("John", "Smith");
		
		assertEquals("John Smith", n.toString());
	}
	
	/**
	 * Test method for valid Name.valueOf method.
	 */
	@Test
	public void testNameValueOf() {
		Name n = Name.valueOf("John Smith");
		// An object should have been created
		assertNotNull(n);
	}
	
	/**
	 * Test method for invalid Name.valueOf method.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNameInvalidValueOf() {
		Name.valueOf("John Q Smith");
		// No assertion, test method should throw expected Exception type
	}

}
