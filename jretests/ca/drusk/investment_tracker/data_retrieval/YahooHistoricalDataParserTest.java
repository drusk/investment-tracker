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

import static ca.drusk.investment_tracker.matchers.YahooDataBeanMatcher.equalsBean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.drusk.investment_tracker.utils.IOUtils;

/**
 * Unit tests for {@link YahooHistoricalDataFetcher}.
 * 
 * @author drusk
 * 
 */
public class YahooHistoricalDataParserTest {

	private YahooHistoricalDataParser underTest;

	private String readToString(String name) throws IOException {
		return IOUtils.readStreamToString(getClass().getResourceAsStream(name));
	}

	@Before
	public void setUp() {
		underTest = new YahooHistoricalDataParser();
	}

	@Test
	public void testParseCsvToBean() throws IOException, ParseException {
		String csvData = readToString("google_data.csv");
		List<YahooDataBean> beans = underTest.parse(csvData);

		assertEquals(beans.size(), 2062);
		assertThat(
				beans.get(0),
				equalsBean("2012-10-23", "672.01", "687.33", "672.00", "680.35", "2916600",
						"680.35"));
		assertThat(
				beans.get(2061),
				equalsBean("2004-08-19", "100.00", "104.06", "95.96", "100.34", "22351900",
						"100.34"));
	}

}
