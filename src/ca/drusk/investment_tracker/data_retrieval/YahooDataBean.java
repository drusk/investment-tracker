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

import java.util.Date;

/**
 * Stores the data related to an API call to Yahoo financial services for all
 * available historical data for a specific symbol.
 * 
 * @author drusk
 * 
 */
public class YahooDataBean {

	private Date date;

	private String open;

	private String high;

	private String low;

	private String close;

	private String volume;

	private String adjustedClose;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getAdjustedClose() {
		return adjustedClose;
	}

	public void setAdjustedClose(String adjustedClose) {
		this.adjustedClose = adjustedClose;
	}

	/* Beans should have no-arg constructors */
	public YahooDataBean() {
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("date: ").append(date).append(", ");
		sb.append("open: ").append(open).append(", ");
		sb.append("high: ").append(high).append(", ");
		sb.append("low: ").append(low).append(", ");
		sb.append("close: ").append(close).append(", ");
		sb.append("volume: ").append(volume).append(", ");
		sb.append("adjusted close: ").append(adjustedClose);
		return sb.toString();
	}
	
}
