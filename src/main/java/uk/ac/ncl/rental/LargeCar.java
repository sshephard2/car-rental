package uk.ac.ncl.rental;

/**
 * LargeCar class.  Implements elements of the Car interface specific to small cars.
 * @author Stephen Shephard
 * @version 1.0
 */
public final class LargeCar extends AbstractCar {
	
	/**
	 * Fuel tank capacity for LargeCar is set to constant 60 litres.
	 */
	private static final int CAPACITY = 60;
	
	/**
	 * Constructs a LargeCar with the given registration number.
	 * @param rnString the registration number of the car as a String.
	 */
	public LargeCar(String rnString) {
		// Call the Constructor of the superclass i.e. AbstractCar
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
		
		// LargeCar consumes fuel at 10km/litre for the first 50km, 15km/litre thereafter
		// Fuel consumption is measured in whole litres always rounded up
		int fuelConsumed;
		if (km <= 50) {
			fuelConsumed = (km%10 == 0 ? km/10 : km/10 + 1);
		} else {
			fuelConsumed = 5 + ((km-50)%15 == 0 ? (km-50)/15 : (km-50)/15 + 1);
		}
		// Calls consumeFuel from AbstractCar
		this.consumeFuel(fuelConsumed);
		return fuelConsumed;
	}
	
	/**
	 * Return a string representation of this Name.
	 * @return the string representation of the car in the format "LargeCar",[registration number],[fuel]
	 */
	@Override public String toString() {
		// Calls getRegistrationNumber and getFuel from AbstractCar
		return "LargeCar," + this.getRegistrationNumber() + "," + this.getFuel();
	}
}
