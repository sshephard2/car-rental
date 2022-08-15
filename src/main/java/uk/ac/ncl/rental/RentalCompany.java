package uk.ac.ncl.rental;

import java.util.*;

/**
 * RentalCompany class.  The RentalCompany class represents a Car Rental company.
 * It comprises the fleet of cars and a collection of rentals (associations between Car and Person).
 * @author Stephen Shephard
 * @version 1.0
 */
public final class RentalCompany {

	public static final String SMALL_CAR = "SmallCar";
	public static final String LARGE_CAR = "LargeCar";
	/**
	 * Set of cars in the company's fleet.
	 */
	private final Set<Car> fleet;
	
	/**
	 * A map, implemented as a HashMap, representing rentals i.e. associations between Person and Car objects.
	 */
	private final Map<Person, Car> rentals; // Person will be the key as it is immutable
	
	/**
	 * Constructs a RentalCompany.  No parameters.
	 */
	public RentalCompany() {
		// Create empty fleet and rentals structures
		fleet = new HashSet<>();
		rentals = new HashMap<>();
	}
	
	/**
	 * Add a car to the RentalCompany's fleet.
	 * @param car Car object being added to fleet.
	 * @throws IllegalArgumentException if the car parameter is null.
	 * @throws IllegalStateException if the car can't be added to the fleet e.g. because it is a duplicate of one already in there.
	 */
	public void addCar(Car car) {
		// Check parameter
		if (car == null) {
			throw new IllegalArgumentException("Car parameter cannot be null");
		}
		// Try to add car, if it fails throw an exception
		if (!fleet.add(car)) {
			throw new IllegalStateException("Can't add car " + car);
		}
	}
	
	/**
	 * This method returns the number of cars of the specified type that are available to rent.
	 * @param typeOfCar String containing "SmallCar" or "LargeCar".
	 * @return the number of available cars as an integer.
	 * @throws IllegalArgumentException if a null or invalid typeOfCar parameter are supplied.
	 */
	public int availableCars(String typeOfCar) {
		// Check parameters
		if (typeOfCar == null) {
			throw new IllegalArgumentException("typeOfCar parameter cannot be null");
		}
		if (!typeOfCar.equals(SMALL_CAR) && !typeOfCar.equals(LARGE_CAR)) {
			throw new IllegalArgumentException("typeOfCar parameter must be SmallCar or LargeCar");
		}

		// A car is available if it exists in the fleet but not in the rentals structure
		if (typeOfCar.equals(SMALL_CAR))
			return (int) fleet.stream().filter(c -> c instanceof SmallCar && !rentals.containsValue(c)).count();

		// Not small car, so must be large car
		return (int) fleet.stream().filter(c -> c instanceof LargeCar && !rentals.containsValue(c)).count();
	}
	
	/**
	 * This method returns a collection of all the cars currently rented out (if any).
	 * @return the Set of all cars currently rented out as a HashMap.
	 */
	public Set<Car> getRentedCars() {
		// Defensive copy of set of cars from rentals HashMap
		return new HashSet<>(rentals.values());
	}
	
	/**
	 * Given a person, this method returns the car they are currently renting (if any).
	 * @param person the Person object believed to be a rentor.
	 * @return the car rented by the person (or null) as a Car object.
	 * @throws IllegalArgumentException if a null person parameter is supplied.
	 */
	public Car getCar(Person person) {
		// Check person parameter
		if (person == null) {
			throw new IllegalArgumentException("person parameter cannot be null");
		}
		if (!rentals.containsKey(person)) {
			return null;
		}

		// Not a defensive copy of the car object (even if this were possible) as this car must be driveable
		return rentals.get(person);
	}
	
 	/**
	 * This method determines whether the person is eligible to rent a car of the specified type and, if there is a car available, issues it.
	 * @param person the Person wishing to rent a car.
	 * @param drivingLicence the rentor's driving licence.
	 * @param typeOfCar the type of car required as a String containing "SmallCar" or "LargeCar".
	 * @return true if a car can be issued, false otherwise as a boolean.
	 * @throws IllegalArgumentException if any null parameter or invalid typeOfCar are supplied.
	 */
	public boolean issueCar(Person person, DrivingLicence drivingLicence, String typeOfCar) {
		checkParameters(person, drivingLicence, typeOfCar);

		if (personCannotRentCar(person, drivingLicence, typeOfCar)) return false;

		// There must be cars of the requested type available
		if (this.availableCars(typeOfCar) == 0) {
			return false;
		}

		// There is an available car, so go find it
		Optional<Car> foundCar = Optional.empty();
		if (typeOfCar.equals(SMALL_CAR))
			foundCar = fleet.stream().filter(c -> c instanceof SmallCar && !rentals.containsValue(c)).findFirst();
		if (typeOfCar.equals(LARGE_CAR))
			foundCar = fleet.stream().filter(c -> c instanceof LargeCar && !rentals.containsValue(c)).findFirst();

		if (foundCar.isEmpty()) return false;
		Car car = foundCar.orElseThrow();
	 
		// Amount of fuel in Litres required to fill the car's tank
		int fuelRequired = car.getCapacity() - car.getFuel();
		// Ensure the car has a full tank of petrol at the start of the rental
		car.fillTank(fuelRequired);	

		// Associate the car with the person renting it
		// Note: unnecessary to create a defensive copy of person as Person is immutable
		rentals.put(person, car);
		
		// Set the car's rental state to true
		car.setRental(true);

		return true;
	}

	private boolean personCannotRentCar(Person person, DrivingLicence drivingLicence, String typeOfCar) {
		// The driving licence must be full
		if (!drivingLicence.getFull()) {
			return true;
		}

		// The Person cannot rent more than one car at a time
		if (rentals.containsKey(person)) {
			return true;
		}

		// To rent a small car, they must be at least 20 years old
		// To rent a large car, they must be at least 25 years old
		Calendar cBirthCheck = Calendar.getInstance();
		cBirthCheck.add(Calendar.YEAR, (typeOfCar.equals(SMALL_CAR) ? -20 : -25));
		if (person.getBirthDate().compareTo(cBirthCheck.getTime()) > 0) {
			return true;
		}

		// To rent a small car, they must have held their licence for at least 1 year
		// To rent a large car, they must have held their licence for at least 5 years
		Calendar cIssuedCheck = Calendar.getInstance();
		cIssuedCheck.add(Calendar.YEAR, (typeOfCar.equals(SMALL_CAR) ? -1 : -5));
		return drivingLicence.getIssued().compareTo(cIssuedCheck.getTime()) > 0;
	}

	private static void checkParameters(Person person, DrivingLicence drivingLicence, String typeOfCar) {
		if (person == null) {
			throw new IllegalArgumentException("person parameter cannot be null");
		}
		if (drivingLicence == null) {
			throw new IllegalArgumentException("drivingLicence parameter cannot be null");
		}
		if (typeOfCar == null) {
			throw new IllegalArgumentException("typeOfCar parameter cannot be null");
		}
		if (!typeOfCar.equals(SMALL_CAR) && !typeOfCar.equals(LARGE_CAR)) {
			throw new IllegalArgumentException("typeOfCar parameter must be SmallCar or LargeCar");
		}
	}

	/**
	 * This method terminates the given person's rental contract.
	 * If a person attempts to terminate a nonexistent contract, this method does nothing.
	 * @param person a Person renting a car.
	 * @return the amount of fuel in litres required to fill the car's tank, as an integer, or zero if there was no rental contract.
	 */
	public int terminateRental(Person person) {
		Car car = this.getCar(person); //getCar will throw an exception if the person is null
		if (car == null) {
			// If the given person does not have a rental contract, do nothing and return zero litres of fuel to fill
			return 0;
		}
		
		// Amount of fuel in Litres required to fill the car's tank
		int fuelRequired = car.getCapacity() - car.getFuel();
		
		// The method removes the record of the rental from the company's records (disassociating the car from the person)
		rentals.remove(person);
		
		// Set the car's rental state to false
		car.setRental(false);
		
		return fuelRequired;
	}
}
