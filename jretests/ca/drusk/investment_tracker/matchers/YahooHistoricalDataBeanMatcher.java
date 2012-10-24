package ca.drusk.investment_tracker.matchers;

import java.text.ParseException;
import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import ca.drusk.investment_tracker.data_retrieval.YahooHistoricalDataBean;
import ca.drusk.investment_tracker.utils.DateParser;

/**
 * Matcher for {@link YahooHistoricalDataBean}s.
 * 
 * @author drusk
 * 
 */
public class YahooHistoricalDataBeanMatcher extends
		TypeSafeMatcher<YahooHistoricalDataBean> {

	private final YahooHistoricalDataBean expected;

	public YahooHistoricalDataBeanMatcher(YahooHistoricalDataBean expected) {
		this.expected = expected;
	}

	@Override
	public boolean matchesSafely(YahooHistoricalDataBean actual) {
		return expected.getDate().equals(actual.getDate())
				&& expected.getOpen().equals(actual.getOpen())
				&& expected.getHigh().equals(actual.getHigh())
				&& expected.getLow().equals(actual.getLow())
				&& expected.getClose().equals(actual.getClose())
				&& expected.getVolume().equals(actual.getVolume())
				&& expected.getAdjustedClose()
						.equals(actual.getAdjustedClose());
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(expected.toString());
	}

	@Factory
	public static Matcher<YahooHistoricalDataBean> equalsBean(
			String dateString, String open, String high, String low,
			String close, String volume, String adjustedClose)
			throws ParseException {
		YahooHistoricalDataBean bean = new YahooHistoricalDataBean();
		bean.setDate(DateParser.parse(dateString));
		bean.setOpen(open);
		bean.setHigh(high);
		bean.setLow(low);
		bean.setClose(close);
		bean.setVolume(volume);
		bean.setAdjustedClose(adjustedClose);
		return new YahooHistoricalDataBeanMatcher(bean);
	}

}
