package com.focustech.portfolio.dto;

public class RealQuoteDTO  {
	float change;
	long lastUpdated;
	float price;
	String ticker;
	
	public float getChange() {
		return change;
	}
	public void setChange(float change) {
		this.change = change;
	}
	public long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
}
