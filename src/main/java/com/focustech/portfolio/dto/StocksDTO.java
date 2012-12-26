package com.focustech.portfolio.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

public class StocksDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	Double amount;
	float change;
	Double gainLoss;
	long lastUpdated;
	Date purchaseDate;
	String purchaseDateDisply;
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	@Min(1)
	Double pricePaid;
	String profitIndicator;
	Integer stockId;
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	@Min(1)
	Integer quantity;
	@NotEmpty
	@Size(min = 1, max = 20)
	String ticker;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public float getChange() {
		return change;
	}
	public void setChange(float change) {
		this.change = change;
	}
	public Double getGainLoss() {
		return gainLoss;
	}
	public void setGainLoss(Double gainLoss) {
		this.gainLoss = gainLoss;
	}
	public long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseDateDisply() {
		return purchaseDateDisply;
	}
	public void setPurchaseDateDisply(String purchaseDateDisply) {
		this.purchaseDateDisply = purchaseDateDisply;
	}
	public Double getPricePaid() {
		return pricePaid;
	}
	public void setPricePaid(Double pricePaid) {
		this.pricePaid = pricePaid;
	}
	public String getProfitIndicator() {
		return profitIndicator;
	}
	public void setProfitIndicator(String profitIndicator) {
		this.profitIndicator = profitIndicator;
	}
	public Integer getStockId() {
		return stockId;
	}
	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
}
