/**
 * 
 */
package uk.ac.ncl.rental;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;
import java.util.Calendar;

import uk.ac.ncl.rental.DrivingLicence;
import uk.ac.ncl.rental.Name;

/**
 * DrivingLicenceTest class.  JUnit test cases for the DrivingLicence class.
 * @author Stephen Shephard
 * @version 1.0
 */
public class DrivingLicenceTest {

	/**
	 * Test method for DrivingLicence Constructor.
	 * Valid Parameters.
	 */
	@Test
	public void testDrivingLicenceValid() {
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
	@Test(expected = IllegalArgumentException.class)
	public void testDrivingLicenceMissingName() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(2016,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		new DrivingLicence(null, issued, true);
		// No assert statement - should throw an exception
	}
	
	/**
	 * Test method for DrivingLicence Constructor.
	 * Missing date.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDrivingLicenceMissingDate() {
		new DrivingLicence(new Name("David", "Daniels"), null, true);
		// No assert statement - should throw an exception
	}
	
	/**
	 * Test method for DrivingLicence Constructor.
	 * Invalid date.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDrivingLicenceInvalidDate() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(2999,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		new DrivingLicence(new Name("Edward", "Evans"), issued, true);
		// No assert statement - should throw an exception
	}

	/**
	 * Test method for DrivingLicence.getLicenceNumber method.
	 */
	@Test
	public void testGetLicenceNumber() {
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
	public void testGetFull() {
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
	public void testGetIssued() {
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
	public void testToString() {
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(1998,Calendar.FEBRUARY,11);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(new Name("Graham", "Peters"), issued, true);
		
		assertEquals("GP-1998-1,11/02/1998,true", dl.toString());
	}

}
