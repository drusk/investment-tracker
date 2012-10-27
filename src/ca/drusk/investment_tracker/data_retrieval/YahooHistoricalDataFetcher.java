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
