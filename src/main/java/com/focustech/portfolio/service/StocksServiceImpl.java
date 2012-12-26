package com.focustech.portfolio.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.focustech.portfolio.dao.StocksDAO;
import com.focustech.portfolio.domain.Stocks;

/**
 * Spring service that handles CRUD requests for Stocks entities
 * 
 */

@Service("StocksService")
@Transactional
public class StocksServiceImpl implements StocksService {

	/**
	 * DAO injected by Spring that manages Stocks entities
	 * 
	 */
	@Autowired
	private StocksDAO stocksDAO;

	/**
	 * Instantiates a new UsersServiceImpl.
	 *
	 */
	public StocksServiceImpl() {
	}

	/**
	 */
	@Transactional
	public Stocks findStockByStockId(Integer stockId) {
		return stocksDAO.findStockByStockId(stockId);
	}
	
	/**
	 * Return all Stocks entity
	 * 
	 */
	@Transactional
	public List<Stocks> findAllStocks(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Stocks>(stocksDAO.findAllStocks(startResult, maxRows));
	}

	/**
	 * Delete an existing Stocks entity
	 * 
	 */
	@Transactional
	public void deleteStocks(Stocks stock) {
		stocksDAO.remove(stock);
		stocksDAO.flush();
	}

	/**
	 * Save an existing Stocks entity
	 * 
	 */
	@Transactional
	public void saveStocks(Stocks stocks) {
		Stocks existingStocks = stocksDAO.findStockByStockId(stocks.getStockId());

		if (existingStocks != null) {
			if (existingStocks != stocks) {
				BeanUtils.copyProperties(stocks, existingStocks);
			}
			stocks = stocksDAO.store(existingStocks);
		} else {
			stocks = stocksDAO.store(stocks);
		}
		stocksDAO.flush();
	}

	/**
	 * Load an existing Stocks entity
	 * 
	 */
	@Transactional
	public Set<Stocks> loadStocks() {
		return stocksDAO.findAllStocks();
	}

	@Transactional
	public Stocks findStockByTicker(String ticker) {
		return stocksDAO.findStockByTicker(ticker);
		
	}
	
}
