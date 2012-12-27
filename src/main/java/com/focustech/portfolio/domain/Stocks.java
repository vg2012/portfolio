package com.focustech.portfolio.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllStocks", query = "select myStocks from Stocks myStocks"),
		@NamedQuery(name = "findStockByStockId", query = "select myStocks from Stocks myStocks where myStocks.stockId = ?1"),
		@NamedQuery(name = "findStockByTicker", query = "select myStocks from Stocks myStocks where myStocks.ticker =  ?1")})
@Table(name = "stocks")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "portfoliomanagement/com/focustech/portfolio/domain", name = "Stocks")
@XmlRootElement(namespace = "portfoliomanagement/com/focustech/portfolio/domain")
public class Stocks implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "stock_id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue
	@XmlElement
	Integer stockId;
	/**
	 */

	@Column(name = "ticker", length = 50, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ticker;
	/**
	 */

	@Column(name = "price", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Double price;
	/**
	 */

	@Column(name = "quantity", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer quantity;

	/**
	 */
	@Column(name = "purchase_date", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Date purchaseDate;

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
