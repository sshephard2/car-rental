package uk.ac.ncl.rental;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.rental.Person;
import uk.ac.ncl.rental.Name;
import java.util.Date;
import java.util.Calendar;

/**
 * PersonTest class.  JUnit test cases for the Person class.
 * @author Stephen Shephard
 * @version 1.0
 *
 */
public class PersonTest {

	/**
	 * Test method for Person Constructor.
	 * Valid parameters.
	 */
	@Test
	public void testPerson() {
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1971,Calendar.DECEMBER,18);
		Date dDOB = cDOB.getTime();
		Person p = new Person(new Name("Stephen", "Shephard"), dDOB);

		// An object should have been created
		assertNotNull(p);
	}
	
	/**
	 * Test method for Person Constructor.
	 * Missing name.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPersonMissingName() {
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1982,Calendar.MARCH,4);
		Date dDOB = cDOB.getTime();
		new Person(null, dDOB);
		// No assert statement - should throw an exception
	}
	
	/**
	 * Test method for Person Constructor.
	 * Missing date.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPersonMissingDate() {
		new Person(new Name("Jane", "Smith"), null);
		// No assert statement - should throw an exception
	}
	
	/**
	 * Test method for Person Constructor.
	 * Invalid date.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPersonInvalidDate() {
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(2999,Calendar.MARCH,4);
		Date dDOB = cDOB.getTime();
		new Person(new Name("Michael", "Brown"), dDOB);
		// No assert statement - should throw an exception
	}

	/**
	 * Test method for Person.getName method.
	 */
	@Test
	public void testGetName() {
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1990,Calendar.APRIL,15);
		Date dDOB = cDOB.getTime();
		Person p = new Person(new Name("Maria", "Jones"), dDOB);
		
		assertEquals(new Name("Maria", "Jones"), p.getName());
	}

	/**
	 * Test method for Person.getBirthDate method.
	 */
	@Test
	public void testGetBirthDate() {
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1979,Calendar.SEPTEMBER,7);
		Date dDOB1 = cDOB.getTime();
		Date dDOB2 = cDOB.getTime();
		
		Person p = new Person(new Name("Patrick", "Chan"), dDOB1);
		
		assertEquals(dDOB2, p.getBirthDate());
	}
	
	/**
	 * Test method for Person.equals method.
	 */
	@Test
	public void testEquals() {
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1992,Calendar.MAY,29);
		Date dDOB1 = cDOB.getTime();
		Date dDOB2 = cDOB.getTime();
		Date dDOB3 = cDOB.getTime();
		Person p1 = new Person(new Name("Bill", "Harrison"), dDOB1);
		Person p2 = new Person(new Name("Bill", "Harrison"), dDOB2);
		Person p3 = new Person(new Name("Bill", "Harrison"), dDOB3);
		
		// reflexive
		assertTrue(p1.equals(p1));
		
		// symmetric
		assertTrue(p1.equals(p2));
		assertTrue(p2.equals(p1));
		
		// transitive
		assertTrue(p2.equals(p3));
		assertTrue(p1.equals(p3));
		
		// null test
		assertFalse(p1.equals(null));
		
		// consistent
		for (int i=0; i<100; i++) {
			assertTrue(p1.equals(p2));
		}
	}
	
	/**
	 * Test method for Person.hashCode method.
	 * Tests that when two Person objects are equal, so are their hashcodes.
	 */
	@Test
	public void testHashCode() {
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1987,Calendar.JANUARY,1);
		Date dDOB1 = cDOB.getTime();
		Date dDOB2 = cDOB.getTime();
		Person p1 = new Person(new Name("Amy", "Mclean"), dDOB1);
		Person p2 = new Person(new Name("Amy", "Mclean"), dDOB2);

		assertEquals(p1.hashCode(), p2.hashCode());
	}

	/**
	 * Test method for Person.toString method.
	 */
	@Test
	public void testToString() {
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1975,Calendar.JULY,18);
		Date dDOB = cDOB.getTime();
		Person p = new Person(new Name("Peter", "Thompson"), dDOB);
		
		assertEquals("Peter Thompson,18/07/1975", p.toString());
	}

}
