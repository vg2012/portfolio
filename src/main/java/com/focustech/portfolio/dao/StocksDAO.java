package com.focustech.portfolio.dao;

import java.util.Set;

import org.skyway.spring.util.dao.JpaDao;
import org.springframework.dao.DataAccessException;

import com.focustech.portfolio.domain.Stocks;

/**
 * DAO to manage Users entities.
 * 
 */
public interface StocksDAO extends JpaDao<Stocks> {
	/**
	 * findAllStocks
	 *
	 */
	
	public Set<Stocks> findAllStocks() throws DataAccessException;

	public Set<Stocks> findAllStocks(int startResult, int maxRows) throws DataAccessException;

	/**
	 * findStockByStockId
	 *
	 */
	public Stocks findStockByStockId(Integer stockId) throws DataAccessException;
	
	public Stocks findStockByStockId(Integer userId, int startResult, int maxRows) throws DataAccessException;

	public Stocks findStockByTicker(String ticker) throws DataAccessException;
	

}