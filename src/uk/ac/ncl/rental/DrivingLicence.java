package uk.ac.ncl.rental;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * DrivingLicence class.  A DrivingLicence consists of a LicenceNumber, a Date of issue
 * and an indication of whether the licence is a full driving licence or not.
 * @author Stephen Shephard
 * @version 1.0
 */
public final class DrivingLicence {
	
	/**
	 * LicenseNumber component of a DrivingLicence.
	 */
	private final LicenceNumber licenceNum;
	
	/**
	 * Date of issue of a DrivingLicence.
	 */
	private final Date issued;
	
	/**
	 * Full licence boolean flag - true if the DrivingLicence is a full driving licence, false otherwise.
	 */
	private final boolean full;
	
	/**
	 * Constructs a DrivingLicence from the given Name, Date of issued and whether full flag
	 * @param name Name of licence holder.
	 * @param issued Date of birth of licence.
	 * @param full true if the DrivingLicence is a full driving licence, false otherwise.
	 * @throws IllegalArgumentException if the Name or Date parameters are null or the Date of issue is in the future.
	 */
	public DrivingLicence(Name name, Date issued, boolean full) {
		
		// Check for valid input parameters
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		
		if (issued == null) {
			throw new IllegalArgumentException("Issued date cannot be null");
		}
		
		// Check issued date, cannot be in the future
		Date today = new Date();
		if (issued.after(today)) {
			throw new IllegalArgumentException("Issued date cannot be in the future");
		}

		this.issued = issued;
		this.full = full;
		
		// Extract year from issued date object
		Calendar cIssued = Calendar.getInstance();
		cIssued.setTime(issued);
		int year = cIssued.get(Calendar.YEAR);
		
		licenceNum = new LicenceNumber(name, year);
	}

	/**
	 * Return the licence number.
	 * @return the licence number as a LicenceNumber object.
	 */
	public LicenceNumber getLicenceNumber() {
		// No need for defensive copying as LicenceNumber is immutable
		return licenceNum;
	}
	
	/**
	 * Return the full licence status.
	 * @return true if the DrivingLicence is a full driving licence, false otherwise, as a boolean.
	 */
	public boolean getFull() {
		return full;
	}
	
	/**
	 * Return the date of issue of the licence.
	 * @return date of issue as a Date object.
	 */
	public Date getIssued() {
		// Use defensive copying to ensure Issued Date object is immutable (as Date is not)
		return (Date) issued.clone();
	}
	
	/**
	 * Return a string representation of this DrivingLicence.
	 * @return the string representation of the LicenceNumber, the string representation of the date of issue in the format [dd/MM/yyyy],
	 * and the full status (true or false) separated by comma i.e. [initials]-[year]-[serialnum],[dd/MM/yyyy],[true|false]
	 */
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return licenceNum + "," + dateFormat.format(issued) + "," + full;
	}

}
