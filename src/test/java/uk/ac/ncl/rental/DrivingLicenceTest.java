/**
 * 
 */
package uk.ac.ncl.rental;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DrivingLicenceTest class.  JUnit test cases for the DrivingLicence class.
 * @author Stephen Shephard
 * @version 1.0
 */
class DrivingLicenceTest {

	/**
	 * Test method for DrivingLicence Constructor.
	 * Valid Parameters.
	 */
	@Test
	void testDrivingLicenceValid() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(2016,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(new Name("Andrew", "Adams"), issued, true);

		// An object should have been created
		assertNotNull(dl);
	}
	
	/**
	 * Test method for DrivingLicence Constructor.
	 * Missing name.
	 */
	@Test
	void testDrivingLicenceMissingName() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(2016,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		assertThrows(IllegalArgumentException.class, () -> new DrivingLicence(null, issued, true));
	}
	
	/**
	 * Test method for DrivingLicence Constructor.
	 * Missing date.
	 */
	@Test
	void testDrivingLicenceMissingDate() {
		final Name davidDaniels = new Name("David", "Daniels");
		assertThrows(IllegalArgumentException.class, () -> new DrivingLicence(davidDaniels, null, true));
	}
	
	/**
	 * Test method for DrivingLicence Constructor.
	 * Invalid date.
	 */
	@Test
	void testDrivingLicenceInvalidDate() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(2999,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		final Name edwardEvans = new Name("Edward", "Evans");
		assertThrows(IllegalArgumentException.class, () -> new DrivingLicence(edwardEvans, issued, true));
	}

	/**
	 * Test method for DrivingLicence.getLicenceNumber method.
	 */
	@Test
	void testGetLicenceNumber() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(2015,Calendar.JUNE,7);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(new Name("Chloe", "Hart"), issued, false);
		
		assertEquals("CH-2015-1", dl.getLicenceNumber().toString());
	}

	/**
	 * Test method for DrivingLicence.getFull method.
	 */
	@Test
	void testGetFull() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(2012,Calendar.NOVEMBER,19);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(new Name("Michael", "Sharpe"), issued, true);
		
		assertTrue(dl.getFull());
	}

	/**
	 * Test method for DrivingLicence.getIssued method.
	 */
	@Test
	void testGetIssued() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(2015,Calendar.MARCH,21);
		Date issued1 = cIssued.getTime();
		Date issued2 = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(new Name("Tom", "Smithson"), issued1, true);
		
		assertEquals(issued2, dl.getIssued());
	}
	
	/**
	 * Test method for DrivingLicence.toString method.
	 */
	@Test
	void testToString() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(1998,Calendar.FEBRUARY,11);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(new Name("Graham", "Peters"), issued, true);
		
		assertEquals("GP-1998-1,11/02/1998,true", dl.toString());
	}

}
