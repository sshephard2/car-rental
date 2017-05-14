package uk.ac.ncl.rental.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.rental.SmallCar;
import uk.ac.ncl.rental.LargeCar;

/**
 * CarTest class.  JUnit test cases for the SmallCar and LargeCar classes.
 * @author Stephen Shephard
 * @version 1.0
 *
 */
public class CarTest {

	/**
	 * Test method for SmallCar Constructor.
	 */
	@Test
	public void testSmallCar() {
		SmallCar car = new SmallCar("AB01 NCL");	
		// An object should have been created
		assertNotNull(car);
	}
	
	/**
	 * Test method for SmallCar Constructor.
	 * Invalid registration number.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidSmallCar() {
		new SmallCar("ABXY NCL");	
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for SmallCar Constructor.
	 * Should not allow duplicate registration number.
	 */
	@Test(expected = IllegalStateException.class)
	public void testDuplicateSmallCar() {
		new SmallCar("AB01 ABC");
		new SmallCar("AB01 ABC"); // Duplicate Registration Number
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for LargeCar Constructor.
	 */
	@Test
	public void testLargeCar() {
		LargeCar car = new LargeCar("LC01 NCL");	
		// An object should have been created
		assertNotNull(car);
	}
	
	/**
	 * Test method for LargeCar Constructor.
	 * Invalid registration number.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidLargeCar() {
		new LargeCar("LCXY NCL");	
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for LargeCar Constructor.
	 * Should not allow duplicate registration number.
	 */
	@Test(expected = IllegalStateException.class)
	public void testDuplicateLargeCar() {
		new LargeCar("LC01 ABC");
		new LargeCar("LC01 ABC"); // Duplicate Registration Number
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for SmallCar and LargeCar Constructors.
	 * Should not allow duplicate registration number across both types of car.
	 */
	@Test(expected = IllegalStateException.class)
	public void testDuplicateCar() {
		new SmallCar("AB11 NEA");
		new LargeCar("AB11 NEA"); // Duplicate Registration Number
		// No assertion, test method should throw expected Exception type
	}

	/**
	 * Test method for SmallCar.getCapacity method.
	 * Capacity of a small car should be 49 litres.
	 */
	@Test
	public void testGetCapacitySmall() {
		SmallCar car = new SmallCar("AB02 MSC");
		assertEquals(49, car.getCapacity());
	}

	/**
	 * Test method for LargeCar.getCapacity method.
	 * Capacity of a large car should be 60 litres.
	 */
	@Test
	public void testGetCapacityLarge() {
		LargeCar car = new LargeCar("CD02 MSC");
		assertEquals(60, car.getCapacity());
	}
	
	/**
	 * Test method for SmallCar.getRegistrationNumber method.
	 */
	@Test
	public void testGetRegistrationNumberSmall() {
		SmallCar car = new SmallCar("AB05 TBD");
		assertEquals("AB05 TBD", car.getRegistrationNumber().toString());
	}
	
	/**
	 * Test method for LargeCar.getRegistrationNumber method.
	 */
	@Test
	public void testGetRegistrationNumberLarge() {
		LargeCar car = new LargeCar("LC05 TBD");
		assertEquals("LC05 TBD", car.getRegistrationNumber().toString());
	}

	/**
	 * Test method for SmallCar.getFuel method.
	 */
	@Test
	public void testGetFuelSmall() {
		SmallCar car = new SmallCar("AB03 COL");
		assertEquals(0, car.getFuel());
		car.fillTank(1);
		assertEquals(1, car.getFuel());
	}

	/**
	 * Test method for LargeCar.getFuel method.
	 */
	@Test
	public void testGetFuelLarge() {
		LargeCar car = new LargeCar("LC03 COL");
		assertEquals(0, car.getFuel());
		car.fillTank(1);
		assertEquals(1, car.getFuel());
	}
	
	/**
	 * Test method for SmallCar.isTankFull method.
	 */
	@Test
	public void testIsTankFullSmall() {
		SmallCar car = new SmallCar("AB04 TBD");
		assertFalse(car.isTankFull());
		car.fillTank(49);
		assertTrue(car.isTankFull());
	}
	
	/**
	 * Test method for LargeCar.isTankFull method.
	 */
	@Test
	public void testIsTankFullLarge() {
		LargeCar car = new LargeCar("LC04 TBD");
		assertFalse(car.isTankFull());
		car.fillTank(60);
		assertTrue(car.isTankFull());
	}

	/**
	 * Test method for LargeCar.fillTank method.
	 * Under capacity.
	 */
	@Test
	public void testFillTankLargeUnder() {
		LargeCar car = new LargeCar("LC06 XXX");
		assertEquals(50, car.fillTank(50));
	}
	
	/**
	 * Test method for LargeCar.fillTank method.
	 * Over capacity from empty.
	 */
	@Test
	public void testFillTankLargeOverFromEmpty() {
		LargeCar car = new LargeCar("LC07 YYY");
		assertEquals(60, car.fillTank(65));
	}
	
	/**
	 * Test method for LargeCar.fillTank method.
	 * Over capacity from partial.
	 */
	@Test
	public void testFillTankLargeOverFromPartial() {
		LargeCar car = new LargeCar("LC08 ZZZ");
		assertEquals(50, car.fillTank(50));
		assertEquals(10, car.fillTank(20));
	}
	
	/**
	 * Test method for SmallCar.fillTank method.
	 * Under capacity.
	 */
	@Test
	public void testFillTankSmallUnder() {
		SmallCar car = new SmallCar("AB06 XXX");
		assertEquals(40, car.fillTank(40));
	}
	
	/**
	 * Test method for SmallCar.fillTank method.
	 * Over capacity from empty.
	 */
	@Test
	public void testFillTankSmallOverFromEmpty() {
		SmallCar car = new SmallCar("AB07 YYY");
		assertEquals(49, car.fillTank(50));
	}
	
	/**
	 * Test method for SmallCar.fillTank method.
	 * Over capacity from partial.
	 */
	@Test
	public void testFillTankSmallOverFromPartial() {
		SmallCar car = new SmallCar("AB08 ZZZ");
		assertEquals(30, car.fillTank(30));
		assertEquals(19, car.fillTank(30));
	}
	
	/**
	 * Test method for SmallCar.isRented method.
	 */
	@Test
	public void testSmallCarIsRented() {
		SmallCar car = new SmallCar("AB11 GHJ");
		assertFalse(car.isRented());
	}
	
	/**
	 * Test method for SmallCar.setRental method.
	 */
	@Test
	public void testSmallCarSetRental() {
		SmallCar car = new SmallCar("AB12 KLM");
		car.setRental(true);
		assertTrue(car.isRented());
	}
	
	/**
	 * Test method for LargeCar.isRented method.
	 */
	@Test
	public void testLargeCarIsRented() {
		LargeCar car = new LargeCar("LC11 GHJ");
		assertFalse(car.isRented());
	}
	
	/**
	 * Test method for LargeCar.setRental method.
	 */
	@Test
	public void testLargeCarSetRental() {
		LargeCar car = new LargeCar("LC12 KLM");
		car.setRental(true);
		assertTrue(car.isRented());
	}
	
	/**
	 * Test method for SmallCar.toString method.
	 */
	@Test
	public void testSmallCarToString() {
		SmallCar car = new SmallCar("AB09 ABC");
		assertEquals("SmallCar,AB09 ABC,0", car.toString());
	}
	
	/**
	 * Test method for LargeCar.toString method.
	 */
	@Test
	public void testLargeCarToString() {
		LargeCar car = new LargeCar("AB10 DEF");
		assertEquals("LargeCar,AB10 DEF,0", car.toString());
	}
	
	/**
	 * Test method for SmallCar.drive method.
	 * Invalid (negative) distance.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDriveSmallInvalidDistance() {
		SmallCar car = new SmallCar("AB15 WXZ");
		car.drive(-1);
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for SmallCar.drive method.
	 * Invalid as car not rented.
	 */
	@Test(expected = IllegalStateException.class)
	public void testDriveSmallNotRented() {
		SmallCar car = new SmallCar("AB13 NPQ");
		car.drive(10);
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for SmallCar.drive method.
	 * Rented but no fuel.
	 */
	@Test
	public void testDriveSmallNoFuel() {
		SmallCar car = new SmallCar("AB14 RST");
		car.setRental(true);
		assertEquals(0, car.drive(10));
	}
	
	/**
	 * Test method for SmallCar.drive method.
	 * Journey within tank fuel.
	 */
	@Test
	public void testDriveSmallUnderCapacity() {
		SmallCar car = new SmallCar("AB16 AAB");
		car.setRental(true);
		car.fillTank(10);
		assertEquals(0, car.drive(0)); // 0 km requires 0 litres
		assertEquals(1, car.drive(1)); // 1 km requires 1 litres
		assertEquals(1, car.drive(19)); // 19 km requires 1 litres
		assertEquals(1, car.drive(20)); // 20 km requires 1 litres
		assertEquals(2, car.drive(21)); // 21 km requires 2 litres
	}
	
	/**
	 * Test method for SmallCar.drive method.
	 * Journey over tank fuel.
	 */
	@Test
	public void testDriveSmallOverCapacity() {
		SmallCar car = new SmallCar("AB17 ACD");
		car.setRental(true);
		car.fillTank(1); // 1 litre in tank
		assertEquals(2, car.drive(40)); // 40 km requires 2 litres
		assertEquals(-1, car.getFuel()); // should be -1 litres "in tank"
	}

	/**
	 * Test method for LargeCar.drive method.
	 * Invalid (negative) distance.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDriveLargeInvalidDistance() {
		LargeCar car = new LargeCar("LC15 WXZ");
		car.drive(-1);
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for LargeCar.drive method.
	 * Invalid as car not rented.
	 */
	@Test(expected = IllegalStateException.class)
	public void testDriveLargeNotRented() {
		LargeCar car = new LargeCar("LC13 NPQ");
		car.drive(10);
		// No assertion, test method should throw expected Exception type
	}
	
	/**
	 * Test method for LargeCar.drive method.
	 * Rented but no fuel.
	 */
	@Test
	public void testDriveLargeNoFuel() {
		LargeCar car = new LargeCar("LC14 RST");
		car.setRental(true);
		assertEquals(0, car.drive(10));
	}
	
	/**
	 * Test method for LargeCar.drive method.
	 * Journey within tank fuel.
	 */
	@Test
	public void testDriveLargeUnderCapacity() {
		LargeCar car = new LargeCar("LC16 AAB");
		car.setRental(true);
		car.fillTank(60);
		assertEquals(0, car.drive(0)); // 0 km requires 0 litres
		assertEquals(1, car.drive(1)); // 1 km requires 1 litres
		assertEquals(1, car.drive(9)); // 9 km requires 1 litres
		assertEquals(1, car.drive(10)); // 10 km requires 1 litres
		assertEquals(2, car.drive(11)); // 11 km requires 2 litres
		assertEquals(5, car.drive(49)); // 49 km requires 5 litres
		assertEquals(5, car.drive(50)); // 50 km requires 5 litres
		assertEquals(6, car.drive(51)); // 51 km requires 6 litres
		assertEquals(6, car.drive(60)); // 60 km requires 6 litres
		assertEquals(6, car.drive(65)); // 65 km requires 6 litres
		assertEquals(7, car.drive(66)); // 66 km requires 7 litres
	}
	
	/**
	 * Test method for LargeCar.drive method.
	 * Journey over tank fuel.
	 */
	@Test
	public void testDriveLargeOverCapacity() {
		LargeCar car = new LargeCar("LC17 ACD");
		car.setRental(true);
		car.fillTank(1); // 1 litre in tank
		assertEquals(2, car.drive(20)); // 20 km requires 2 litres
		assertEquals(-1, car.getFuel()); // should be -1 litres "in tank"
	}
}
