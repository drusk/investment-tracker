package ca.drusk.investment_tracker.matchers;

import java.text.ParseException;
import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import ca.drusk.investment_tracker.data_retrieval.YahooDataBean;
import ca.drusk.investment_tracker.utils.DateParser;

/**
 * Matcher for {@link YahooDataBean}s.
 * 
 * @author drusk
 * 
 */
public class YahooHistoricalDataBeanMatcher extends
		TypeSafeMatcher<YahooDataBean> {

	private final YahooDataBean expected;

	public YahooHistoricalDataBeanMatcher(YahooDataBean expected) {
		this.expected = expected;
	}

	@Override
	public boolean matchesSafely(YahooDataBean actual) {
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
	public static Matcher<YahooDataBean> equalsBean(
			String dateString, String open, String high, String low,
			String close, String volume, String adjustedClose)
			throws ParseException {
		YahooDataBean bean = new YahooDataBean();
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
