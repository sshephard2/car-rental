/**
 * 
 */
package uk.ac.ncl.rental;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RentalCompanyTest class.  JUnit test cases for the RentalCompany class.
 * @author Stephen Shephard
 * @version 1.0
 */
class RentalCompanyTest {

	/**
	 * Test method for RentalCompany Constructor.
	 */
	@Test
	void testRentalCompany() {
		RentalCompany rentCo = new RentalCompany();
		assertNotNull(rentCo);
	}

	/**
	 * Test method for RentalCompany.addCar method.
	 */
	@Test
	void testAddCar() {
		RentalCompany rentCo = new RentalCompany();
		for (int i=1; i<=20; i++) {
			SmallCar sc = new SmallCar(String.format("SC%1$02d AAA", i));
			assertDoesNotThrow(() -> rentCo.addCar(sc));
		}
		for (int i=1; i<=10; i++) {
			LargeCar lc = new LargeCar(String.format("LC%1$02d AAA", i));
			assertDoesNotThrow(() -> rentCo.addCar(lc));
		}
	}
	
	/**
	 * Test method for RentalCompany.addCar method.
	 * Car parameter may not be null.
	 */
	@Test
	void testAddCarInvalid() {
		RentalCompany rentCo = new RentalCompany();

		assertThrows(IllegalArgumentException.class, () -> rentCo.addCar(null));
	}
	
	/**
	 * Test method for RentalCompany.addCar method.
	 * Should not add duplicate Car.
	 */
	@Test
	void testAddCarDuplicate() {
		RentalCompany rentCo = new RentalCompany();

		SmallCar sc = new SmallCar("SC01 FFF");
		rentCo.addCar(sc);
		assertThrows(IllegalStateException.class, () -> rentCo.addCar(sc));
	}

	/**
	 * Test method for RentalCompany.availableCars method.
	 */
	@Test
	void testAvailableCars() {
		RentalCompany rentCo = new RentalCompany();
		for (int i=1; i<=20; i++) {
			SmallCar sc = new SmallCar(String.format("SC%1$02d CCC", i));
			rentCo.addCar(sc);
		}
		for (int i=1; i<=10; i++) {
			LargeCar lc = new LargeCar(String.format("LC%1$02d CCC", i));
			rentCo.addCar(lc);
		}
		assertEquals(20, rentCo.availableCars("SmallCar"));
		assertEquals(10, rentCo.availableCars("LargeCar"));
	}
	
	/**
	 * Test method for RentalCompany.availableCars method.
	 * typeOfCar parameter may not be null.
	 */
	@Test
	void testAvailableCarsNullCarType() {
		RentalCompany rentCo = new RentalCompany();

		assertThrows(IllegalArgumentException.class, () -> rentCo.availableCars(null));
	}
	
	/**
	 * Test method for RentalCompany.availableCars method.
	 * typeOfCar parameter must be SmallCar or LargeCar.
	 */
	@Test
	void testAvailableCarsInvalidCarType() {
		RentalCompany rentCo = new RentalCompany();

		assertThrows(IllegalArgumentException.class, () -> rentCo.availableCars("MediumCar"));
	}

	/**
	 * Test method for RentalCompany.getRentedCars method.
	 */
	@Test
	void testGetRentedCars() {
		RentalCompany rentCo = new RentalCompany();
		Set<Car> rentedSet;
		
		// Only one large car, to test that this one is issued
		SmallCar sc = new SmallCar("SC01 GGG");
		rentCo.addCar(sc);
		
		// Only one large car, to test that this one is issued
		LargeCar lc = new LargeCar("LC01 GGG");
		rentCo.addCar(lc);
		
		Calendar cDOB1 = Calendar.getInstance();
		cDOB1.set(1971,Calendar.DECEMBER,18);
		Date dDOB1 = cDOB1.getTime();
		Name name1 = new Name("Stephen", "Shephard");
		Person p1 = new Person(name1, dDOB1);
		Calendar cIssued1 = Calendar.getInstance();
		cIssued1.set(1990,Calendar.OCTOBER,20);
		Date issued1 = cIssued1.getTime();
		DrivingLicence dl1 = new DrivingLicence(name1, issued1, true);
		
		Calendar cDOB2 = Calendar.getInstance();
		cDOB2.set(1978,Calendar.APRIL,5);
		Date dDOB2 = cDOB2.getTime();
		Name name2 = new Name("Jane", "Smith");
		Person p2 = new Person(name2, dDOB2);
		Calendar cIssued2 = Calendar.getInstance();
		cIssued2.set(2008,Calendar.JULY,17);
		Date issued2 = cIssued2.getTime();
		DrivingLicence dl2 = new DrivingLicence(name2, issued2, true);
		
		// No rented cars at this point
		rentedSet = rentCo.getRentedCars();
		
		// size of set returned by getRentedCars should be 0 i.e. contains no cars
		assertEquals(0, rentedSet.size());
		
		rentCo.issueCar(p1, dl1, "LargeCar");
		rentCo.issueCar(p2, dl2, "SmallCar");
		
		rentedSet = rentCo.getRentedCars();
		
		// getRentedCars should return the SmallCar and LargeCar issued
		assertTrue(rentedSet.contains(sc));
		assertTrue(rentedSet.contains(lc));
		
		// size of set returned by getRentedCars should be 2 i.e. contains no other cars
		assertEquals(2, rentedSet.size());
	}

	/**
	 * Test method for RentalCompany.getCar method.
	 */
	@Test
	void testGetCar() {
		RentalCompany rentCo = new RentalCompany();
		for (int i=1; i<=20; i++) {
			SmallCar sc = new SmallCar(String.format("SC%1$02d EEE", i));
			rentCo.addCar(sc);
		}

		// Only one large car, to test that this one is issued and returned
		LargeCar lc = new LargeCar("LC01 EEE");
		rentCo.addCar(lc);
		
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1971,Calendar.DECEMBER,18);
		Date dDOB = cDOB.getTime();
		Name name = new Name("Stephen", "Shephard");
		Person p = new Person(name, dDOB);
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(1990,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(name, issued, true);
		
		rentCo.issueCar(p, dl, "LargeCar");
		assertSame(lc, rentCo.getCar(p));
	}
	
	/**
	 * Test method for RentalCompany.getCar method.
	 * Person parameter may not be null.
	 */
	@Test
	void testGetCarPersonNull() {
		RentalCompany rentCo = new RentalCompany();

		assertThrows(IllegalArgumentException.class, () -> rentCo.getCar(null));
	}

	/**
	 * Test method for RentalCompany.issueCar method.
	 * Successful issue.
	 */
	@Test
	void testIssueCarSuccess() {
		RentalCompany rentCo = new RentalCompany();
		for (int i=1; i<=20; i++) {
			SmallCar sc = new SmallCar(String.format("SC%1$02d DDD", i));
			rentCo.addCar(sc);
		}
		for (int i=1; i<=10; i++) {
			LargeCar lc = new LargeCar(String.format("LC%1$02d DDD", i));
			rentCo.addCar(lc);
		}
		
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1971,Calendar.DECEMBER,18);
		Date dDOB = cDOB.getTime();
		Name name = new Name("Stephen", "Shephard");
		Person p = new Person(name, dDOB);
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(1990,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(name, issued, true);
		
		// Check that there were 10 large cars available to rent
		assertEquals(10, rentCo.availableCars("LargeCar"));
		
		// Check car issued returns true
		assertTrue(rentCo.issueCar(p, dl, "LargeCar"));
		
		// Check car's tank is full
		assertTrue(rentCo.getCar(p).isTankFull());
		
		// Check that there are now only 9 available large cars to rent
		assertEquals(9, rentCo.availableCars("LargeCar"));
	}
	
	/**
	 * Test method for RentalCompany.issueCar method.
	 * Person parameter may not be null.
	 */
	@Test
	void testIssueCarPersonNull() {
		RentalCompany rentCo = new RentalCompany();
		
		Name name = new Name("Stephen", "Shephard");
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(1990,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(name, issued, true);

		assertThrows(IllegalArgumentException.class, () -> rentCo.issueCar(null, dl, "LargeCar"));
	}
	
	/**
	 * Test method for RentalCompany.issueCar method.
	 * Driving Licence parameter may not be null.
	 */
	@Test
	void testIssueCarDrivingLicenceNull() {
		RentalCompany rentCo = new RentalCompany();
		
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1971,Calendar.DECEMBER,18);
		Date dDOB = cDOB.getTime();
		Name name = new Name("Stephen", "Shephard");
		Person p = new Person(name, dDOB);

		assertThrows(IllegalArgumentException.class, () -> rentCo.issueCar(p, null, "LargeCar"));
	}
	
	/**
	 * Test method for RentalCompany.issueCar method.
	 * Type of car parameter may not be null.
	 */
	@Test
	void testIssueCarTypeOfCarNull() {
		RentalCompany rentCo = new RentalCompany();
		
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1971,Calendar.DECEMBER,18);
		Date dDOB = cDOB.getTime();
		Name name = new Name("Stephen", "Shephard");
		Person p = new Person(name, dDOB);
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(1990,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(name, issued, true);

		assertThrows(IllegalArgumentException.class, () -> rentCo.issueCar(p, dl, null));
	}
	
	/**
	 * Test method for RentalCompany.issueCar method.
	 * typeOfCar parameter must be SmallCar or LargeCar.
	 */
	@Test
	void testIssueCarTypeOfCarInvalid() {
		RentalCompany rentCo = new RentalCompany();
		
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1971,Calendar.DECEMBER,18);
		Date dDOB = cDOB.getTime();
		Name name = new Name("Stephen", "Shephard");
		Person p = new Person(name, dDOB);
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(1990,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(name, issued, true);

		assertThrows(IllegalArgumentException.class, () -> rentCo.issueCar(p, dl, "MediumCar"));
	}

	/**
	 * Test method for RentalCompany.issueCar method.
	 * Unsuccessful issue.
	 */
	//TODO - make test independent of run date @Test
	void testIssueCarFailure() {
		RentalCompany rentCo = new RentalCompany();

		// Add one SmallCar and two LargeCars to the fleet
		SmallCar sc = new SmallCar("SC01 HHH");
		rentCo.addCar(sc);
		for (int i=1; i<=2; i++) {
			LargeCar lc = new LargeCar(String.format("LC%1$02d HHH", i));
			rentCo.addCar(lc);
		}
		
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1997,Calendar.OCTOBER,25);
		Date dDOB_19yo = cDOB.getTime();
		cDOB.set(1996,Calendar.OCTOBER,25);
		Date dDOB_20yo = cDOB.getTime();
		cDOB.set(1991,Calendar.OCTOBER,25);
		Date dDOB_25yo = cDOB.getTime();
		Date dDOB_25yo_copy = cDOB.getTime();
		
		Name name_19yo = new Name("Grace", "Liu");
		Person p_19yo = new Person(name_19yo, dDOB_19yo);
		
		Name name_20yo = new Name("James", "Andrews");
		Person p_20yo = new Person(name_20yo, dDOB_20yo);
		Name name_20yo_twin = new Name("Michael", "Andrews");
		Person p_20yo_twin = new Person(name_20yo_twin, dDOB_20yo);
		
		Name name_25yo = new Name("Abigail", "Tyler");
		Person p_25yo = new Person(name_25yo, dDOB_25yo);
		Name name_25yo_copy = new Name("Abigail", "Tyler");
		Person p_25yo_copy = new Person(name_25yo_copy, dDOB_25yo_copy);
		
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(2016,Calendar.OCTOBER,25);
		Date issued_new = cIssued.getTime();
		cIssued.set(2015,Calendar.OCTOBER,25);
		Date issued_1yo = cIssued.getTime();
		cIssued.set(2011,Calendar.OCTOBER,25);
		Date issued_5yo = cIssued.getTime();
		
		DrivingLicence dl = new DrivingLicence(name_25yo, issued_5yo, false);
		
		// Can't issue if Driving Licence isn't full
		assertFalse(rentCo.issueCar(p_25yo, dl, "SmallCar"));
		
		// Can't issue a SmallCar (or LargeCar) if DrivingLicence has been held for less than 1 year
		dl = new DrivingLicence(name_25yo, issued_new, true);
		assertFalse(rentCo.issueCar(p_25yo, dl, "SmallCar"));
		assertFalse(rentCo.issueCar(p_25yo, dl, "LargeCar"));
		
		// Can't issue a SmallCar (or LargeCar) if Person is less than 20 years old
		dl = new DrivingLicence(name_19yo, issued_1yo, true);
		assertFalse(rentCo.issueCar(p_19yo, dl, "SmallCar"));
		assertFalse(rentCo.issueCar(p_19yo, dl, "LargeCar"));

		// Can't issue a LargeCar if Person is less than 25 years old
		dl = new DrivingLicence(name_20yo, issued_1yo, true);
		assertFalse(rentCo.issueCar(p_20yo, dl, "LargeCar"));
		
		// Can't issue a LargeCar if if DrivingLicence has been held for less than 5 years
		dl = new DrivingLicence(name_25yo, issued_1yo, true);
		assertFalse(rentCo.issueCar(p_25yo, dl, "LargeCar"));

		// Can't rent more than one car at a time
		dl = new DrivingLicence(name_25yo, issued_5yo, true);
		assertTrue(rentCo.issueCar(p_25yo, dl, "LargeCar"));
		assertFalse(rentCo.issueCar(p_25yo, dl, "LargeCar"));
		assertFalse(rentCo.issueCar(p_25yo, dl, "SmallCar")); // Still not true if you try to rent a different type of car
		dl = new DrivingLicence(name_25yo_copy, issued_5yo, true);
		assertFalse(rentCo.issueCar(p_25yo_copy, dl, "LargeCar")); // Still not true with a different Person object that is logically equal
		
		// Can't rent if no cars available of the requested type
		dl = new DrivingLicence(name_20yo, issued_1yo, true);
		assertTrue(rentCo.issueCar(p_20yo, dl, "SmallCar"));
		dl = new DrivingLicence(name_20yo_twin, issued_1yo, true);
		assertFalse(rentCo.issueCar(p_20yo_twin, dl, "SmallCar"));	
	}
	
	/**
	 * Test method for RentalCompany.terminateRental method.
	 */
	@Test
	void testTerminateRental() {
		RentalCompany rentCo = new RentalCompany();
		for (int i=1; i<=20; i++) {
			SmallCar sc = new SmallCar(String.format("SC%1$02d JJJ", i));
			rentCo.addCar(sc);
		}
		for (int i=1; i<=10; i++) {
			LargeCar lc = new LargeCar(String.format("LC%1$02d JJJ", i));
			rentCo.addCar(lc);
		}
		
		Calendar cDOB = Calendar.getInstance();
		cDOB.set(1971,Calendar.DECEMBER,18);
		Date dDOB = cDOB.getTime();
		Name name = new Name("Stephen", "Shephard");
		Person p = new Person(name, dDOB);
		Name name2 = new Name("John", "Smith");
		Person p2 = new Person(name2, dDOB);
		Calendar cIssued = Calendar.getInstance();
		cIssued.set(1990,Calendar.OCTOBER,20);
		Date issued = cIssued.getTime();
		DrivingLicence dl = new DrivingLicence(name, issued, true);
		
		// Check that there were 20 small cars available to rent
		assertEquals(20, rentCo.availableCars("SmallCar"));
		
		// Issue car
		rentCo.issueCar(p, dl, "SmallCar");
				
		// Check that there are now only 19 available small cars to rent
		assertEquals(19, rentCo.availableCars("SmallCar"));
		
		// Get car
		Car car = rentCo.getCar(p);
		assertNotNull(car);
		
		// Drive the car
		car.drive(1);
		
		// Attempt to terminate non-existent rental (nothing should happen, method should return zero)
		assertEquals(0, rentCo.terminateRental(p2));
		
		// Check that there are still 19 available small cars to rent
		assertEquals(19, rentCo.availableCars("SmallCar"));
			
		// Terminate valid rental - 1 litre of fuel should be required
		assertEquals(1, rentCo.terminateRental(p));
		
		// Check that there are now 20 available small cars to rent
		assertEquals(20, rentCo.availableCars("SmallCar"));
	}
}
