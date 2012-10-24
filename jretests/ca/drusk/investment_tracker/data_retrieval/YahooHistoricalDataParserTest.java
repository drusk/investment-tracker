package ca.drusk.investment_tracker.data_retrieval;

import static ca.drusk.investment_tracker.matchers.YahooHistoricalDataBeanMatcher.equalsBean;
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
		List<YahooHistoricalDataBean> beans = underTest.parse(csvData);

		assertEquals(beans.size(), 2062);
		assertThat(
				beans.get(0),
				equalsBean("2012-Oct-23", 672.01, 687.33, 672, 680.35, 2916600,
						680.35));
		assertThat(
				beans.get(2061),
				equalsBean("2004-Aug-19", 100, 104.06, 95.96, 100.34, 22351900,
						100.34));
	}

}
