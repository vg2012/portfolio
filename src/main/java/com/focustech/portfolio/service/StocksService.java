package com.focustech.portfolio.service;

import java.util.List;

import com.focustech.portfolio.domain.Stocks;

/**
 * Spring service that handles CRUD requests for Stocks entities
 * 
 */
public interface StocksService {

	/**
	 */
	public Stocks findStockByStockId(Integer stockId);

	/**
	 * Return all Stocks entity
	 * 
	 */
	public List<Stocks> findAllStocks(Integer startResult, Integer maxRows);

	/**
	 * Delete an existing Stocks entity
	 * 
	 */
	public void deleteStocks(Stocks stocks);
	
	/**
	 * Save an existing Stocks entity
	 * 
	 */
	public void saveStocks(Stocks stocks);

	public Stocks findStockByTicker(String ticker);
	
	
}