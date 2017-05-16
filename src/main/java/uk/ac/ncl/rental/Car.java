package uk.ac.ncl.rental;

/**
 * Car interface.  Interface representing all Car implementations.
 * @author Stephen Shephard
 * @version 1.0
 */
public interface Car {

	/**
	 * Method to get the car's registration number.
	 * @return the registration number as a RegistrationNumber object.
	 */
	RegistrationNumber getRegistrationNumber();
	
	/**
	 * Method to get the amount of fuel currently in the fuel tank.
	 * @return the amount of fuel in whole Litres as an integer.
	 */
	int getFuel();
	
	/**
	 * Method that indicates whether the car's fuel tank is full or not.
	 * @return true if the tank is full, false otherwise as a boolean.
	 */
	boolean isTankFull();
	
	/**
	 * Method to get the capacity of the car's fuel tank.
	 * @return the capacity in whole Litres as an integer.
	 */
	int getCapacity();
	
	/**
	 * Method to set the rental status of a car.
	 * @param activeRental set to true if the car has been issued for rental, false otherwise.
	 */
	void setRental(boolean activeRental);
	
	/**
	 * Method to add a given number of whole Litres to the fuel tank (up to the tank's capacity).
	 * @param litres given number of whole Litres to add to tank.
	 * @return how much fuel in whole Litres was added to the tank as an integer.
	 */
	int fillTank(int litres);
	
	/**
	 * Method to "drive" the car for a given number of whole Kilometres.
	 * @param km given number of whole Kilometres to drive.
	 * @return the number of whole Litres of fuel consumed during the journey as an integer.
	 * @throws IllegalArgumentException if an invalid (negative) distance is given.
	 * @throws IllegalStateException if the car cannot be driven.
	 */
	int drive(int km);

}
