package uk.ac.ncl.rental;

import java.util.HashMap;
import java.util.Map;

/**
 * RegistrationNumber class.  A RegistrationNumber consists of two parts:<ul>
 * <li>The first part is two letters followed by two digits</li>
 * <li>The second part is three letters.</li>
 * </ul>
 * @author Stephen Shephard
 * @version 1.0
 */
public final class RegistrationNumber {

	/**
	 * First part - two letters followed by two digits.
	 */
	private final String firstPart;
	
	/**
	 * Second part - three letters.
	 */
	private final String secondPart;
	
	/**
	 * A map, implemented as a HashMap, from the String representation of a RegistrationNumber to theRegistrationNumber object,
	 * used to to enforce uniqueness of Registration Numbers.
	 */
	private static final Map<String, RegistrationNumber> REGNUMBERS = new HashMap<String, RegistrationNumber>();
	
	/**
	 * Constructs a RegistrationNumber from its two parts.
	 * @param first first part of Registration Number.
	 * @param second second part of Registration Number.
	 */
	private RegistrationNumber(String first, String second) {
		// Parameters have already been checked by Factory method
		firstPart = first;
		secondPart = second;
	}
	
	/**
	 * Factory method for RegistrationNumber, ensures that Registration Numbers are unique by checking the internal map to see if
	 * a matching RegistrationNumber has already been created.
	 * @param first first part of Registration Number.
	 * @param second second part of Registration Number.
	 * @return new Registration Number object.
	 * @throws IllegalArgumentException if invalid parameters are given i.e. either part is null or does not match the part's specification.
	 * @throws IllegalStateException if the Registration Number is already in use.
	 */
	public static RegistrationNumber getInstance(String first, String second) {
		// Check parameters
		if (first == null) {
			throw new IllegalArgumentException("First part cannot be null");
		}
		if (!first.matches("[A-Z]{2}\\d{2}")) {
			throw new IllegalArgumentException("First part must be two letters followed by two digits");
		}
		if (second == null) {
			throw new IllegalArgumentException("Second part cannot be null");
		}
		if (!second.matches("[A-Z]{3}")) {
			throw new IllegalArgumentException("Second part must be three letters");
		}
		// Check to see if this Registration Number already exists
		final String key = first + second;
		if (!REGNUMBERS.containsKey(key)) {
			REGNUMBERS.put(key, new RegistrationNumber(first, second));
			return REGNUMBERS.get(key);
		} else {
			throw new IllegalStateException("Registration Number already in use");
		}
	}
	
	/**
	 * Returns the first part of this RegistrationNumber.
	 * @return the first part as a String.
	 */
	public String getFirstPart() {
		return firstPart;
	}
	
	/**
	 * Returns the second part of this RegistrationNumber.
	 * @return the second part as a String.
	 */
	public String getSecondPart() {
		return secondPart;
	}
	
	/**
	 * OReturn a string representation of this RegistrationNumber.
	 * @return the first and last parts of this RegistrationNumber, separated by space, as a String.
	 */
	@Override public String toString() {
		return firstPart + " " + secondPart;
	}

	/**
	 * Returns a RegistrationNumber object from a correctly formatted String.
	 * @param fullname the String to be parsed in the format [FirstPart] [SecondPart].
	 * @return a RegistrationNumber object holding the value represented by the String argument.
	 * @throws IllegalArgumentException if the String cannot be parsed.
	 */
	public static RegistrationNumber valueOf(String rnString) {
		// Check rnString parameter
		if (rnString == null) {
			throw new IllegalArgumentException("Registration Number String cannot be null");
		}
		final String[] parts = rnString.split(" ");
		// Can string be parsed
		if (parts.length!=2) {
			throw new IllegalArgumentException("There must be two parts separated by space");
		}
		final String firstPart = parts[0].equals(null) ? null : parts[0];
		final String secondPart = parts[1].equals(null) ? null : parts[1];
		
		// Call Factory method - throws an exception if this RegistrationNumber is a duplicate
		return RegistrationNumber.getInstance(firstPart, secondPart);
	}
}
