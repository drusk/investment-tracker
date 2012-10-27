/******************************************************************************
 * Copyright (C) 2012 David Rusk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *****************************************************************************/
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
