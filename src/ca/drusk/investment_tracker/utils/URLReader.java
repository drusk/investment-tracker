package ca.drusk.investment_tracker.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Provides utilities for reading contents of a URL.
 * 
 * @author drusk
 * 
 */
public class URLReader {

	/**
	 * Obtains URL contents as a String.
	 * 
	 * @param urlString
	 *            the url to retrieve data from.
	 * @param keepNewlines
	 *            set to <code>true</code> if you want to maintain the newline
	 *            at the end of each line read.
	 * @return String representation of the URL's contents
	 * @throws IOException
	 */
	public static String getText(String urlString, boolean keepNewlines)
			throws IOException {
		URL url = new URL(urlString);

		BufferedReader in = new BufferedReader(new InputStreamReader(url
				.openConnection().getInputStream()));

		StringBuilder response = new StringBuilder();

		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			if (keepNewlines) {
				response.append("\n");
			}
		}

		in.close();

		return response.toString();
	}

	/*
	 * Primitive test case/sanity check.
	 */
	public static void main(String[] args) {
		String text = null;
		try {
			text = getText("http://chart.yahoo.com/table.csv?s=GOOG", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(text);
	}

}
