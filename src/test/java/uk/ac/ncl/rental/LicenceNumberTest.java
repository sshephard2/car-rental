package uk.ac.ncl.rental;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LicenceNumberTest class.  JUnit test cases for the LicenceNumber class.
 * @author Stephen Shephard
 * @version 1.0
 */
class LicenceNumberTest {

	/**
	 * Test method for LicenceNumber Constructor.
	 * Valid parameters.
	 */
	@Test
	void testLicenseNumberValid() {
		LicenceNumber ln = new LicenceNumber(new Name("Andrew", "Adams"), 1980);

		// An object should have been created
		assertNotNull(ln);
	}
	
	/**
	 * Test method for LicenceNumber Constructor.
	 * Missing name parameter.
	 */
	@Test
	void testLicenseNumberMissingName() {
		assertThrows(IllegalArgumentException.class, () -> new LicenceNumber(null, 1981));
	}
	
	/**
	 * Test method for LicenceNumber Constructor.
	 * Invalid year parameter.
	 */
	@Test
	void testLicenseNumberInvalidYear() {
		final Name davidDaniels = new Name("David", "Daniels");
		assertThrows(IllegalArgumentException.class, () -> new LicenceNumber(davidDaniels, 2999));
	}
	
	/**
	 * Test method for LicenceNumber Constructor.
	 * Duplicate initials and year.
	 */
	@Test
	void testLicenseNumberDuplication() {
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
	void testGetInitials() {
		LicenceNumber ln = new LicenceNumber(new Name("Frank", "Field"), 1984);
		assertEquals("FF", ln.getInitials());
	}

	/**
	 * Test method for LicenceNumber.getYear method.
	 */
	@Test
	void testGetYear() {
		LicenceNumber ln = new LicenceNumber(new Name("Gwen", "Jones"), 1985);
		assertEquals(1985, ln.getYear());
	}

	/**
	 * Test method for LicenceNumber.getSerialNum method.
	 */
	@Test
	void testGetSerialNum() {
		LicenceNumber ln = new LicenceNumber(new Name("Harriet", "Smith"), 1986);
		assertEquals(1, ln.getSerialNum());
		ln = new LicenceNumber(new Name("Harry", "Sones"), 1986);
		assertEquals(2, ln.getSerialNum());
	}

	/**
	 * Test method for LicenceNumber.toString method.
	 */
	@Test
	void testToString() {
		LicenceNumber ln = new LicenceNumber(new Name("James", "Davies"), 1987);
		assertEquals("JD-1987-1", ln.toString());
	}

}
