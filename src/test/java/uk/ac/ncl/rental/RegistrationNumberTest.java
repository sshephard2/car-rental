package uk.ac.ncl.rental;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.rental.RegistrationNumber;

/**
 * RegistrationNumberTest class.  JUnit test cases for the RegistrationNumber class.
 * @author Stephen Shephard
 * @version 1.0
 *
 */
public class RegistrationNumberTest {

	/**
	 * Test method for RegistrationNumber Factory method.
	 * Valid parameters.
	 */
	@Test
	public void testGetInstanceValid() {
		RegistrationNumber rn = RegistrationNumber.getInstance("AB01", "LCN");
		
		// An object should have been created
		assertNotNull(rn);
	}

	/**
	 * Test method for RegistrationNumber Factory method.
	 * Missing first parameter.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInstanceMissingFirst() {
		RegistrationNumber.getInstance(null, "NCL");
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for RegistrationNumber Factory method.
	 * Missing second parameter.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInstanceMissingSecond() {
		RegistrationNumber.getInstance("AB01", null);
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for RegistrationNumber Factory method.
	 * Invalid first parameter.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInstanceInvalidFirst() {
		RegistrationNumber.getInstance("AB0", "NCL");
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for RegistrationNumber Factory method.
	 * Invalid second parameter.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInstanceInvalidSecond() {
		RegistrationNumber.getInstance("AB01", "NC");
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for RegistrationNumber Factory method.
	 * Can't create duplicates.
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetInstanceDuplication() {
		RegistrationNumber.getInstance("AB02", "MSC");
		RegistrationNumber.getInstance("AB02", "MSC");
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for RegistrationNumber.getFirstPart method.
	 */
	@Test
	public void testGetfirstPart() {
		RegistrationNumber rn = RegistrationNumber.getInstance("AB03", "CLO");
		assertEquals("AB03", rn.getFirstPart());
	}

	/**
	 * Test method for RegistrationNumber.getSecondPart method.
	 */
	@Test
	public void testGetsecondPart() {
		RegistrationNumber rn = RegistrationNumber.getInstance("AB04", "CAR");
		assertEquals("CAR", rn.getSecondPart());
	}

	/**
	 * Test method for RegistrationNumber.toString method.
	 */
	@Test
	public void testToString() {
		RegistrationNumber rn = RegistrationNumber.getInstance("AB05", "XYZ");
		assertEquals("AB05 XYZ", rn.toString());
	}

	/**
	 * Test method for valid RegistrationNumber.valueOf method.
	 */
	@Test
	public void testRegistrationNumberValueOf() {
		RegistrationNumber rn = RegistrationNumber.valueOf("AB06 BBC");
		// An object should have been created
		assertNotNull(rn);
	}
	
	/**
	 * Test method for invalid RegistrationNumber.valueOf method.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRegistrationNumberInvalidValueOf() {
		RegistrationNumber.valueOf("AB06 123");
		// No assertion, test method should throw expected Exception type
	}

	/**
	 * Test method for duplicate RegistrationNumber.valueOf method.
	 */
	@Test(expected = IllegalStateException.class)
	public void testRegistrationNumberDuplicateValueOf() {
		RegistrationNumber.valueOf("AB07 BBC");
		RegistrationNumber.valueOf("AB07 BBC");
		// No assertion, test method should throw expected Exception type
	}
	
}
