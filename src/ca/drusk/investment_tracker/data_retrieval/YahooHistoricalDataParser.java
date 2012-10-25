package ca.drusk.investment_tracker.data_retrieval;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ca.drusk.investment_tracker.utils.DateParser;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Parses data from the CSV format returned by the Yahoo API.
 * 
 * @author drusk
 * 
 */
public class YahooHistoricalDataParser {

	public List<YahooHistoricalDataBean> parse(String csvData)
			throws IOException, ParseException {
		CSVReader reader = new CSVReader(new StringReader(csvData));

		List<String[]> allLines = reader.readAll();
		assert allLines.size() >= 1 : "no data retrieved";
		String[] headers = allLines.remove(0);
		// TODO generate an exception which can be handled by client if data is
		// not as expected
		assert headers[0].equals("Date");
		assert headers[1].equals("Open");
		assert headers[2].equals("High");
		assert headers[3].equals("Low");
		assert headers[4].equals("Close");
		assert headers[5].equals("Volume");
		assert headers[6].equals("Adj Close");

		List<YahooHistoricalDataBean> beans = new ArrayList<YahooHistoricalDataBean>();
		for (String[] line : allLines) {
			YahooHistoricalDataBean bean = new YahooHistoricalDataBean();
			bean.setDate(DateParser.parse(line[0]));
			bean.setOpen(line[1]);
			bean.setHigh(line[2]);
			bean.setLow(line[3]);
			bean.setClose(line[4]);
			bean.setVolume(line[5]);
			bean.setAdjustedClose(line[6]);
			beans.add(bean);
		}
		
		return beans;
	}
}
