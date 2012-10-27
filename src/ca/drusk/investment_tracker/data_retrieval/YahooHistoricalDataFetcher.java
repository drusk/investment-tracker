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
package ca.drusk.investment_tracker.data_retrieval;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;

import ca.drusk.investment_tracker.utils.URLReader;

/**
 * Used to retrieve historical data from Yahoo's financial APIs.
 * 
 * @author drusk
 * 
 */
public class YahooHistoricalDataFetcher {

	private String baseUrl = "http://chart.yahoo.com/table.csv?s=";

	/**
	 * Generates the URL with the data for the requested symbol. Makes sure that
	 * the URL is encoded.
	 * 
	 * @param symbol
	 *            the symbol to search for
	 * @return the encoded URL which can be used to retrieve data for the symbol
	 */
	private String getEncodedUrl(String symbol) {
		assert symbol != null;
		String url = baseUrl;
		try {
			url += URLEncoder.encode(symbol, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// there should be no problem using UTF-8
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * Retrieves historical data from the Yahoo financial API.
	 * 
	 * @param symbol
	 *            the symbol whose data will be looked up.
	 * @return a list of beans representing the data retrieved from the Yahoo
	 *         API.
	 * @throws IOException
	 * @throws ParseException 
	 */
	public List<YahooDataBean> fetchDataForSymbol(String symbol)
			throws IOException, ParseException {
		String csvData = URLReader.getText(getEncodedUrl(symbol), true);
		return new YahooHistoricalDataParser().parse(csvData);
	}

}
