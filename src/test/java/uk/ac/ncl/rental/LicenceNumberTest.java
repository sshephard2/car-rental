package uk.ac.ncl.rental;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.rental.LicenceNumber;
import uk.ac.ncl.rental.Name;

/**
 * LicenceNumberTest class.  JUnit test cases for the LicenceNumber class.
 * @author Stephen Shephard
 * @version 1.0
 */
public class LicenceNumberTest {

	/**
	 * Test method for LicenceNumber Constructor.
	 * Valid parameters.
	 */
	@Test
	public void testLicenseNumberValid() {
		LicenceNumber ln = new LicenceNumber(new Name("Andrew", "Adams"), 1980);

		// An object should have been created
		assertNotNull(ln);
	}
	
	/**
	 * Test method for LicenceNumber Constructor.
	 * Missing name parameter.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testLicenseNumberMissingName() {
		new LicenceNumber(null, 1981);
		// No assert statement - should throw an exception
	}
	
	/**
	 * Test method for LicenceNumber Constructor.
	 * Invalid year parameter.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testLicenseNumberInvalidYear() {
		new LicenceNumber(new Name("David", "Daniels"), 2020);
		// No assert statement - should throw an exception
	}
	
	/**
	 * Test method for LicenceNumber Constructor.
	 * Duplicate initials and year.
	 */
	@Test
	public void testLicenseNumberDuplication() {
		LicenceNumber ln1 = new LicenceNumber(new Name("Edward", "Evans"), 1983);
		LicenceNumber ln2 = new LicenceNumber(new Name("Edith", "Edwards"), 1983);
		LicenceNumber ln3 = new LicenceNumber(new Name("Edwin", "E"), 1983);
		assertEquals("EE-1983-1", ln1.toString());
		assertEquals("EE-1983-2", ln2.toString());
		assertEquals("EE-1983-3", ln3.toString());
	}

	/**
	 * Test method for LicenceNumber.getInitials method.
	 */
	@Test
	public void testGetInitials() {
		LicenceNumber ln = new LicenceNumber(new Name("Frank", "Field"), 1984);
		assertEquals("FF", ln.getInitials());
	}

	/**
	 * Test method for LicenceNumber.getYear method.
	 */
	@Test
	public void testGetYear() {
		LicenceNumber ln = new LicenceNumber(new Name("Gwen", "Jones"), 1985);
		assertEquals(1985, ln.getYear());
	}

	/**
	 * Test method for LicenceNumber.getSerialNum method.
	 */
	@Test
	public void testGetSerialNum() {
		LicenceNumber ln = new LicenceNumber(new Name("Harriet", "Smith"), 1986);
		assertEquals(1, ln.getSerialNum());
		ln = new LicenceNumber(new Name("Harry", "Sones"), 1986);
		assertEquals(2, ln.getSerialNum());
	}

	/**
	 * Test method for LicenceNumber.toString method.
	 */
	@Test
	public void testToString() {
		LicenceNumber ln = new LicenceNumber(new Name("James", "Davies"), 1987);
		assertEquals("JD-1987-1", ln.toString());
	}

}
