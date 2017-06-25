package uk.ac.ncl.rental;

/**
 * SmallCar class.  Implements elements of the Car interface specific to small cars.
 * @author Stephen Shephard
 * @version 1.0
 */
public final class SmallCar extends AbstractCar {
	
	/**
	 * Fuel tank capacity for SmallCar is set to constant 49 litres.
	 */
	private static final int CAPACITY = 49;
	
	/**
	 * Constructs a SmallCar with the given registration number.
	 * @param rnString the registration number of the car as a String.
	 */
	public SmallCar(String rnString) {
		// Call the Constructor of the superclass i.e. AbstractCar
		// Defensive copying of rnString is not required as String is an immutable class
		// Note: RegistrationNumber.valueOf throws an exception if a duplicate registration number is given
		super(RegistrationNumber.valueOf(rnString), CAPACITY);
	}
	
	/**
	 * Method to "drive" the car for a given number of whole Kilometres.
	 * @param km given number of whole Kilometres to drive.
	 * @return the number of whole Litres of fuel consumed during the journey as an integer.
	 * @throws IllegalArgumentException if an invalid (negative) distance is given.
	 * @throws IllegalStateException if the car cannot be driven.
	 */
	public int drive(int km) {
		// Check parameters
		if (km < 0) {
			throw new IllegalArgumentException("Cannot drive a negative distance");
		}
		if (!this.isRented()) {
			// Calls this.toString
			throw new IllegalStateException("Car " + this + " cannot be driven as it is not currently rented");
		}
		if (this.getFuel() <= 0) {
			// If there is no fuel in the tank, car cannot be driven, but return zero consumption rather than throw exception
			return 0;
		}
		
		// SmallCar consumes fuel at a uniform rate of 20km/litre
		// Fuel consumption is measured in whole litres always rounded up
		int fuelConsumed = (km%20 == 0 ? km/20 : km/20 + 1);
		// Calls consumeFuel from AbstractCar
		this.consumeFuel(fuelConsumed);
		return fuelConsumed;
	}
	
	/**
	 * Return a string representation of this Name.
	 * @return the string representation of the car in the format "SmallCar",[registration number],[fuel]
	 */
	@Override public String toString() {
		// Calls getRegistrationNumber and getFuel from AbstractCar
		return "SmallCar," + this.getRegistrationNumber() + "," + this.getFuel();
	}
}
