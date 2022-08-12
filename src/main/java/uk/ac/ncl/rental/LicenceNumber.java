package uk.ac.ncl.rental;

import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

/**
 * LicenceNumber class, representing the unique licence number of a DrivingLicence.  A LicenceNumber has three components.<ul>
 * <li>The first component is the concatenation of the initial of the first name of the driver with the initial of the last name of the driver.</li>
 * <li>The second component is the year of issue of the licence.</li>
 * <li>The third component is an arbitrary serial number. For example, the string representation of the licence number for a licence issued to Mark Smith in 1990 would have the form:
 * <code>MS-1990-10</code> where the 10 is a serial number that, with the initials and year, guarantees the uniqueness of the licence number as a whole.</li>
 * </ul>
 * @author Stephen Shephard
 * @version 1.0
 */
public final class LicenceNumber {
	
	/**
	 * Driver initials - first part of a LicenceNumber.
	 */
	private final String initials;
	
	/**
	 * Year of issue - second part of a LicenceNumber.
	 */
	private final int year;
	
	/**
	 * Unique serial number - third part of a LicenceNumber.
	 */
	private final int serialnum;
	
	/**
	 * A map, implemented as a HashMap, from the first and second parts of a LicenceNumber to the current highest sequence number for that combination
	 * of parts, used to generate the unique serial numbers.
	 */
	private static final Map<String, Integer> SERIALSEQ = new HashMap<>();
		
	/**
	 * Constructs a LicenceNumber from the given Name and year of issue.
	 * @param name Name of licence holder.
	 * @param year year of issue.
	 * @throws IllegalArgumentException if the Name parameter is null or the year of issue is in the future.
	 */
	public LicenceNumber(Name name, int year) {

		// Check for valid input parameters
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		
		// Check year, cannot be in the future
		Calendar today = Calendar.getInstance();
		int thisYear = today.get(Calendar.YEAR);
		if (year>thisYear) {
			throw new IllegalArgumentException("Year cannot be in the future");
		}
		
		initials = name.getFirstName().substring(0,1) + name.getLastName().substring(0,1);
		this.year = year;
		
		// Get the serial number
		final String key = initials + year;
		if (SERIALSEQ.containsKey(key)) {
			// If this combination of initials and year already exists, increment the serial number
			serialnum = SERIALSEQ.get(key)+1;			
		} else {
			// If this combination of initials and year is new, set the serial number to 1
			serialnum = 1;
		}
		SERIALSEQ.put(key, serialnum);
	}
	
	/**
	 * Return the first part of this licence number, i.e. the driver initials.
	 * @return the driver initials as a String.
	 */
	public String getInitials() {
		return initials;
	}
	
	/**
	 * Return the second part of this licence number, i.e. year of issue
	 * @return the year of issue as an integer.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Return the second part of this licence number, i.e. the serial number
	 * @return the serial number as an integer.
	 */
	public int getSerialNum() {
		return serialnum;
	}
	
	/**
	 * Return a string representation of this LicenceNumber.
	 * @return the string representation of the licence number in the format [initials]-[year]-[serialnum].
	 */
	@Override public String toString() {
		return initials + "-" + year + "-" + serialnum;
	}
}
