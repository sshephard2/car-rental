package uk.ac.ncl.rental;

/**
 * AbstractCar class.  This class provides a partial implementation of the Car interface i.e. those elements common to both SmallCar and LargeCar. 
 * @author Stephen Shephard
 * @version 1.0
 */
public abstract class AbstractCar implements Car {
	
	/**
	 * RegistrationNumber of the car.
	 */
	private final RegistrationNumber regNumber;
	
	/**
	 * Capacity of the fuel tank in whole litres.
	 */
	private final int capacity;
	
	/**
	 * Fuel (in whole litres) currently in the car's tank.
	 */
	private int fuel = 0;
	
	/**
	 * True if the is car currently rented, false otherwise.
	 */
	private boolean activeRental = false;
	
	/**
	 * Constructs an AbstractCar with the given registration number and capacity.
	 * Note this Constructor is package-private as it must be visible by subclasses of AbstractCar.
	 * @param rn the registration number of the car.
	 * @param capacity the capacity of the car's fuel tank.
	 */
	AbstractCar(RegistrationNumber rn, int capacity) {
		// RegistrationNumber is immutable so no need for defensive copying
		// Furthermore as this is an abstract class, the Constructor may only be called from its subclass Constructors
		// which guarantee that rn is not null
		regNumber = rn;
		this.capacity = capacity;
	}
		
	/**
	 * Method to get the car's registration number.
	 * @return the registration number as a RegistrationNumber object.
	 */
	public RegistrationNumber getRegistrationNumber() {
		// RegistrationNumber is immutable so no need for defensive copying
		return regNumber;
	}
	
	/**
	 * Method to get the capacity of the car's fuel tank.
	 * @return the capacity in whole Litres as an integer.
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Method to get whether the car is currently issued for rental.
	 * @return true if the car is currently being rented, false otherwise.
	 */
	public boolean isRented() {
		return activeRental;
	}
	
	/**
	 * Method to set the rental status of a car.
	 * @param activeRental set to true if the car has been issued for rental, false otherwise.
	 */
	public void setRental(boolean activeRental) {
		this.activeRental = activeRental;
	}

	/**
	 * Method to get the amount of fuel currently in the fuel tank.
	 * @return the amount of fuel in whole Litres as an integer.
	 */
	public int getFuel() {
		return fuel;
	}
	
	/**
	 * Method to consume an amount of fuel.
	 * Note that it is allowed to consume more fuel than is in the tank.
	 * @param fuelConsumed a whole number of litres of fuel to consume.
	 */
	public void consumeFuel(int fuelConsumed) {
		fuel = fuel - fuelConsumed;
	}

	/**
	 * Method that indicates whether the car's fuel tank is full or not.
	 * @return true if the tank is full, false otherwise as a boolean.
	 */
	public boolean isTankFull() {
		return fuel == capacity;
	}

	/**
	 * Method to add a given number of whole Litres to the fuel tank (up to the tank's capacity).
	 * @param litres given number of whole Litres to add to tank.
	 * @return how much fuel in whole Litres was added to the tank as an integer.
	 */
	public int fillTank(int litres) {
		int required = capacity - fuel; // litres of fuel needed to fill the tank
		if (litres>required) {
			fuel = capacity;
			return required;
		} else {
			fuel = fuel + litres;
			return litres;
		}
	}

}
