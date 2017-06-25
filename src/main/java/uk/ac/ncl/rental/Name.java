package uk.ac.ncl.rental;

/**
 * Name class.  A Name consists of a first name and a last name of a person.
 * @author Stephen Shephard
 * @version 1.0
 *
 */
public final class Name {

	/**
	 * First name of a person.
	 */
	private final String firstName;
	
	/**
	 * Last name of a person.
	 */
	private final String lastName;
	
	/**
	 * Constructs a Name with the given first and last names.
	 * @param first the first name of the person.
	 * @param last the last name of the person.
	 * @throws IllegalArgumentException if either of the first or last names is null or if either
	 * is not an uppercase letter followed only by lowercase letters.
	 */
	public Name(String first, String last) {
		// Check parameters
		if (first == null) {
			throw new IllegalArgumentException("First name cannot be null");
		}
		if (last == null) {
			throw new IllegalArgumentException("Last name cannot be null");
		}
		if (!first.matches("[A-Z]{1}[a-z]*")) {
			throw new IllegalArgumentException("First name must begin with an uppercase letter followed only by lowercase letters");
		}
		if (!last.matches("[A-Z]{1}[a-z]*")) {
			throw new IllegalArgumentException("Last name must begin with an uppercase letter followed only by lowercase letters");
		}
		firstName = first;
		lastName = last;
	}
	
	/**
	 * Returns the first name of this Name.
	 * @return the first name as a String.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Returns the last name of this Name.
	 * @return the last name as a String.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Compares this Name to the specified operand.
	 * @param op the object to compare with.
	 * @return true if the first name and last name of this Name and the compared object are equal to each other,
	 * false otherwise, as a boolean.
	 */
	@Override public boolean equals(Object op) {
		// equals contract
		
		// reflexive
		if (this == op) return true;
		
		// non-null and instance of Name
		if (!(op instanceof Name)) return false;
		
		// equal if firstName and secondName of both objects is equal
		Name n = (Name) op;
		return (firstName == null ? n.getFirstName() == null : firstName.equals(n.getFirstName()))
				&& (lastName == null ? n.getLastName() == null : lastName.equals(n.getLastName()));
	}
	
	/**
	 * Returns a hash code for this Name.
	 * @return a hash code value for this object as an integer.
	 */
	@Override public int hashCode() {
		int hc = 17;
		hc = hc*37 + (firstName == null ? 0 : firstName.hashCode());
		hc = hc*37 + (lastName == null ? 0 : lastName.hashCode());
		return hc;
	}
	
	/**
	 * Return a string representation of this Name.
	 * @return the first and last names of this Name, separated by space, as a String.
	 */
	@Override public String toString() {
		return firstName + " " + lastName;
	}
	
	/**
	 * Returns a Name object holding the value of the specified String.
	 * @param fullname the String to be parsed in the format [Firstname] [Lastname] i.e. a valid first name followed by a last name separated by space.
	 * @return a Name object holding the value represented by the String argument.
	 * @throws IllegalArgumentException if the String argument cannot be parsed.
	 */
	public static Name valueOf(String fullname) {
		// Check fullname parameter
		if (fullname == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		final String[] names = fullname.split(" ");
		// Can it be parsed
		if (names.length!=2) {
			throw new IllegalArgumentException("There must be two names separated by space");
		}
		final String firstname = names[0].equals(null) ? null : names[0];
		final String lastname = names[1].equals(null) ? null : names[1];
		
		return new Name(firstname, lastname);
	}
}
