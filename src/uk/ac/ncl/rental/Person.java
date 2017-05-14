package uk.ac.ncl.rental;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Person class.  A Person, representing a customer, consists of a Name object and Date object representing name and date of birth of a person.
 * @author Stephen Shephard
 * @version 1.0
 *
 */
public final class Person {
	
	/**
	 * Name of a person.
	 */
	private final Name name;
	
	/**
	 * Date of birth of a person.
	 */
	private final Date birthDate;

	/**
	 * Constructs a Person with the given Name and Date of birth.
	 * @param name Name of person.
	 * @param birth Date of birth of person.
	 * @throws IllegalArgumentException if either the Name or Date parameters is null or if the Date of birth given is in the future.
	 */
	public Person(Name name, Date birth) {
		// Check parameters
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		if (birth == null) {
			throw new IllegalArgumentException("Birth date cannot be null");
		}
		// Check birth date, cannot be in the future
		Date today = new Date();
		if (birth.after(today)) {
			throw new IllegalArgumentException("Date of birth cannot be in the future");
		}
		
		this.name = name; // Name is immutable so defensive copying not needed
		birthDate = new Date(birth.getTime()); // Use defensive copy as Date is mutable
	}
	
	/**
	 * Return the Name of this Person.
	 * @return the name of the person as a Name object.
	 */
	public Name getName() {
		// Name is immutable so defensive copying not needed
		return name;
	}
	
	/**
	 * Return the Date of birth of this Person.
	 * @return the date of birth of the person as a Date object.
	 */
	public Date getBirthDate() {
		// Return defensive copy of Date object to preserve immutability
		return (Date) birthDate.clone();
	}
	
	/**
	 * Compares this Person to the specified operand.
	 * @param op the object to compare with.
	 * @return true if the Name and Date of birth of this Person and the compared object are equal to each other,
	 * false otherwise, as a boolean.
	 */
	public boolean equals(Object op) {
		// equals contract
		
		// reflexive
		if (this == op) return true;
		
		// non-null and instance of Person
		if (!(op instanceof Person)) return false;
		
		// equal if name and birthDate of both objects are equal
		Person p = (Person) op;
		
		return (name == null ? p.getName() == null : name.equals(p.getName()))
				&& (birthDate == null ? p.getBirthDate() == null : birthDate.equals(p.getBirthDate()));
	}
	
	/**
	 * Returns a hash code for this Person.
	 * @return a hash code value for this object as an integer.
	 */
	public int hashCode() {
		int hc = 17;
		hc = hc*37 + (name == null ? 0 : name.hashCode());
		hc = hc*37 + (birthDate == null ? 0 : birthDate.hashCode());
		return hc;
	}
	
	/**
	 * Return a string representation of this Person.
	 * @return the string representation of Name, and the string representation of the date of birth in the format [dd/MM/yyyy]
	 * separated by comma i.e. [Firstname Lastname],[dd/MM/yyyy>]
	 */
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return name + "," + dateFormat.format(birthDate);
	}
}
