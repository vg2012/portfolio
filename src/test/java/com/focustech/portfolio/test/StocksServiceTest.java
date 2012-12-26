package com.focustech.portfolio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.focustech.portfolio.domain.Stocks;
import com.focustech.portfolio.service.StocksService;
/**
 * Class to run the service as a JUnit test. Each operation in the service is a separate test.
 *
 */

@ContextConfiguration(locations = {
		"file:src/test/resources/stocks-security-context.xml",
		"file:src/test/resources/stocks-service-context.xml",
		"file:src/test/resources/stocks-dao-context.xml",
		"file:src/test/resources/stocks-web-context.xml" })

public class StocksServiceTest extends AbstractJUnit4SpringContextTests {

	private static final String TICKER = "ORCL";
	private static final Double PRICE = 18.53;
	private static final Integer QUANTITY = new Integer(1500);
	private static final Date PURCHASE_DATE = new Date();
	private Integer initialstockListSize = 0;
	
	/**
	 * The service being tested, injected by Spring.
	 *
	 */
	@Autowired
	protected StocksService service;
	
	/**
    * setUp overrides the default, We will use it to instantiate our required
    * objects so that we get a clean copy for each test.
    */
    @Before
    public void insertData() {
    	initialstockListSize = service.findAllStocks(-1, -1).size();
        saveStocks();
    }
	
    @After
    public void cleanUp() {
    	deleteStocks();
    }
    
	/**
	 * Operation Unit Test
	 */
	@Test
	public void findStockByTicker() {
	    Stocks returnedStockByTicker = service.findStockByTicker(TICKER);
        assertEquals(TICKER, returnedStockByTicker.getTicker());
	}

	/**
	 * Operation Unit Test
	 * Return all Stocks entity
	 * 
	 */
	@Test
	public void findAllStocks() {
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Stocks> response = service.findAllStocks(startResult, maxRows);
		String returnedTicker = null;
		Stocks returnedStockByTicker = service.findStockByTicker(TICKER);
      	for (Stocks stocks : response) {
			if (returnedStockByTicker.getTicker().equals(stocks.getTicker())) {
				returnedTicker = stocks.getTicker();
				break;
			}
		}
		assertEquals("The ticker should be ORCL. Received = " + returnedTicker, TICKER, returnedTicker);
	
	}
	/**
	 * Operation Unit Test
	 * Save an existing Stocks entity
	 * 
	 */
	@Test
	public void saveStocks() {
		Stocks returnedStockByTicker = service.findStockByTicker(TICKER);
		Integer startResult = 0;
		Integer maxRows = 0;
		List<Stocks> response = null;
		if (null != returnedStockByTicker) {
			Integer newQuantity  = returnedStockByTicker.getQuantity() + QUANTITY;
			Double newPrice = (returnedStockByTicker.getPrice() + PRICE )/2;
			returnedStockByTicker.setQuantity(newQuantity);
			returnedStockByTicker.setPrice(newPrice);
			service.saveStocks(returnedStockByTicker);
			response = service.findAllStocks(startResult, maxRows);
			assertTrue((initialstockListSize + 1) == response.size());
		} else {
			response = service.findAllStocks(startResult, maxRows);
			service.saveStocks(getStockObject());
			assertTrue(initialstockListSize  == response.size());
		}
		
		Stocks returnedTicker = service.findStockByTicker(TICKER);
		
		assertEquals("The ticker should be ORCL. Received = " + returnedTicker, TICKER, returnedTicker.getTicker());	
		
		}


	private void deleteStocks() {
		Stocks returnedStockByTicker = service.findStockByTicker(TICKER);
		service.deleteStocks(returnedStockByTicker);
		Stocks returnedTicker = service.findStockByTicker(TICKER);
		if (null == returnedTicker) {
			assertTrue("The ORCL ticker has been deleted",true);
		}
	}


	private final Stocks getStockObject(){
		 Stocks stocks = new Stocks();
         stocks.setPrice(PRICE);
         stocks.setPurchaseDate(PURCHASE_DATE);
         stocks.setQuantity(QUANTITY);
         stocks.setTicker(TICKER);
         return stocks;
	}
}
