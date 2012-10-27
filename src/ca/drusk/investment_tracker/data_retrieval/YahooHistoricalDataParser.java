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

	public List<YahooDataBean> parse(String csvData)
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

		List<YahooDataBean> beans = new ArrayList<YahooDataBean>();
		for (String[] line : allLines) {
			YahooDataBean bean = new YahooDataBean();
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
