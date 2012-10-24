package ca.drusk.investment_tracker.data_retrieval;

import java.util.Date;

/**
 * Stores the data related to an API call to Yahoo financial services for all
 * available historical data for a specific symbol.
 * 
 * @author drusk
 * 
 */
public class YahooHistoricalDataBean {

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
	public YahooHistoricalDataBean() {
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
