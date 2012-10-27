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
package ca.drusk.investment_tracker.matchers;

import java.text.ParseException;

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
public class YahooDataBeanMatcher extends
		TypeSafeMatcher<YahooDataBean> {

	private final YahooDataBean expected;

	public YahooDataBeanMatcher(YahooDataBean expected) {
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
		return new YahooDataBeanMatcher(bean);
	}

}
