/**
 * 
 */
package uk.ac.ncl.rental;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
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

	private Person personFixture(String firstname, String lastname, long age)
	{
		LocalDate ldDOB = LocalDate.now().minusYears(age);
		Date dDOB = Date.from(ldDOB.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return new Person(new Name(firstname, lastname), dDOB);
	}

	private DrivingLicence dlFixture(Person p, long issuedAge, boolean full)
	{
		LocalDate ldIssued = LocalDate.now().minusYears(issuedAge);
		Date dIssued = Date.from(ldIssued.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return new DrivingLicence(p.getName(), dIssued, full);
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

		Person p1 = personFixture("Stephen", "Shephard", 50);
		DrivingLicence dl1 = dlFixture(p1, 30, true);

		Person p2 = personFixture("Jane", "Smith", 32);
		DrivingLicence dl2 = dlFixture(p2, 17, true);
		
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

		Person p1 = personFixture("Stephen", "Shephard", 50);
		DrivingLicence dl1 = dlFixture(p1, 30, true);
		
		rentCo.issueCar(p1, dl1, "LargeCar");
		assertSame(lc, rentCo.getCar(p1));
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

		Person p1 = personFixture("Stephen", "Shephard", 50);
		DrivingLicence dl1 = dlFixture(p1, 30, true);
		
		// Check that there were 10 large cars available to rent
		assertEquals(10, rentCo.availableCars("LargeCar"));
		
		// Check car issued returns true
		assertTrue(rentCo.issueCar(p1, dl1, "LargeCar"));

		// Check car can be returned
		Car car = rentCo.getCar(p1);
		assertNotNull(car);

		// Check car's tank is full
		assertTrue(car.isTankFull());
		
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

		Person p1 = personFixture("Stephen", "Shephard", 50);
		DrivingLicence dl1 = dlFixture(p1, 30, true);

		assertThrows(IllegalArgumentException.class, () -> rentCo.issueCar(null, dl1, "LargeCar"));
	}
	
	/**
	 * Test method for RentalCompany.issueCar method.
	 * Driving Licence parameter may not be null.
	 */
	@Test
	void testIssueCarDrivingLicenceNull() {
		RentalCompany rentCo = new RentalCompany();

		Person p1 = personFixture("Stephen", "Shephard", 50);

		assertThrows(IllegalArgumentException.class, () -> rentCo.issueCar(p1, null, "LargeCar"));
	}
	
	/**
	 * Test method for RentalCompany.issueCar method.
	 * Type of car parameter may not be null.
	 */
	@Test
	void testIssueCarTypeOfCarNull() {
		RentalCompany rentCo = new RentalCompany();

		Person p1 = personFixture("Stephen", "Shephard", 50);
		DrivingLicence dl1 = dlFixture(p1, 30, true);

		assertThrows(IllegalArgumentException.class, () -> rentCo.issueCar(p1, dl1, null));
	}
	
	/**
	 * Test method for RentalCompany.issueCar method.
	 * typeOfCar parameter must be SmallCar or LargeCar.
	 */
	@Test
	void testIssueCarTypeOfCarInvalid() {
		RentalCompany rentCo = new RentalCompany();

		Person p1 = personFixture("Stephen", "Shephard", 50);
		DrivingLicence dl1 = dlFixture(p1, 30, true);

		assertThrows(IllegalArgumentException.class, () -> rentCo.issueCar(p1, dl1, "MediumCar"));
	}

	/**
	 * Test method for RentalCompany.issueCar method.
	 * Unsuccessful issue.
	 */
	@Test
	void testIssueCarFailure() {
		RentalCompany rentCo = new RentalCompany();

		// Add one SmallCar and two LargeCars to the fleet
		SmallCar sc = new SmallCar("SC01 HHH");
		rentCo.addCar(sc);
		for (int i=1; i<=2; i++) {
			LargeCar lc = new LargeCar(String.format("LC%1$02d HHH", i));
			rentCo.addCar(lc);
		}

		Person p_19yo = personFixture("Grace", "Liu", 19);

		Person p_20yo = personFixture("James", "Andrews", 20);
		Person p_20yo_twin = personFixture("Michael", "Andrews", 20);

		Person p_25yo = personFixture("Abigail", "Tyler", 25);
		Person p_25yo_copy = personFixture("Abigail", "Tyler", 25);
		
		// Can't issue if Driving Licence isn't full
		assertFalse(rentCo.issueCar(p_25yo, dlFixture(p_25yo, 5, false), "SmallCar"));
		
		// Can't issue a SmallCar (or LargeCar) if DrivingLicence has been held for less than 1 year
		assertFalse(rentCo.issueCar(p_25yo, dlFixture(p_25yo, 0, true), "SmallCar"));
		assertFalse(rentCo.issueCar(p_25yo, dlFixture(p_25yo, 0, true), "LargeCar"));
		
		// Can't issue a SmallCar (or LargeCar) if Person is less than 20 years old
		assertFalse(rentCo.issueCar(p_19yo, dlFixture(p_19yo, 1, true), "SmallCar"));
		assertFalse(rentCo.issueCar(p_19yo, dlFixture(p_19yo, 1, true), "LargeCar"));

		// Can't issue a LargeCar if Person is less than 25 years old
		assertFalse(rentCo.issueCar(p_20yo, dlFixture(p_20yo, 1, true), "LargeCar"));
		
		// Can't issue a LargeCar if DrivingLicence has been held for less than 5 years
		assertFalse(rentCo.issueCar(p_25yo, dlFixture(p_25yo, 1, true), "LargeCar"));

		// Can't rent more than one car at a time
		DrivingLicence dl = dlFixture(p_25yo, 5, true);
		assertTrue(rentCo.issueCar(p_25yo, dl, "LargeCar"));
		assertFalse(rentCo.issueCar(p_25yo, dl, "LargeCar"));
		assertFalse(rentCo.issueCar(p_25yo, dl, "SmallCar")); // Still not true if you try to rent a different type of car
		dl = dlFixture(p_25yo_copy, 5, true);
		assertFalse(rentCo.issueCar(p_25yo_copy, dl, "LargeCar")); // Still not true with a different Person object that is logically equal
		
		// Can't rent if no cars available of the requested type
		assertTrue(rentCo.issueCar(p_20yo, dlFixture(p_20yo, 1, true), "SmallCar"));
		assertFalse(rentCo.issueCar(p_20yo_twin, dlFixture(p_20yo_twin, 1, true), "SmallCar"));
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

		Person p1 = personFixture("Stephen", "Shephard", 50);
		DrivingLicence dl1 = dlFixture(p1, 30, true);
		Person p2 = personFixture("John", "Smith", 29);
		
		// Check that there were 20 small cars available to rent
		assertEquals(20, rentCo.availableCars("SmallCar"));
		
		// Issue car
		rentCo.issueCar(p1, dl1, "SmallCar");
				
		// Check that there are now only 19 available small cars to rent
		assertEquals(19, rentCo.availableCars("SmallCar"));
		
		// Get car
		Car car = rentCo.getCar(p1);
		assertNotNull(car);
		
		// Drive the car
		car.drive(1);
		
		// Attempt to terminate non-existent rental (nothing should happen, method should return zero)
		assertEquals(0, rentCo.terminateRental(p2));
		
		// Check that there are still 19 available small cars to rent
		assertEquals(19, rentCo.availableCars("SmallCar"));
			
		// Terminate valid rental - 1 litre of fuel should be required
		assertEquals(1, rentCo.terminateRental(p1));
		
		// Check that there are now 20 available small cars to rent
		assertEquals(20, rentCo.availableCars("SmallCar"));
	}
}
