package ca.drusk.investment_tracker.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Methods for parsing dates from strings.
 * 
 * @author drusk
 * 
 */
public final class DateParser {

	/**
	 * Parses a date from a string in the form yyyy-mm-dd.
	 * 
	 * @param dateString
	 *            the string form of the date to parse.
	 * @return the Date object which has been parsed.
	 * @throws ParseException 
	 */
	public static Date parse(String dateString) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(dateString);
	}

}
