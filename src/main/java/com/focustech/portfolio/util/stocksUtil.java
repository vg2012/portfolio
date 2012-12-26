package com.focustech.portfolio.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.focustech.portfolio.dto.RealQuoteDTO;

public class stocksUtil {
	private static final Log log = LogFactory.getLog(stocksUtil.class);
	private static final stocksUtil stockInfo = new stocksUtil();
	private static HashMap<String, RealQuoteDTO> stocks = new HashMap<String, RealQuoteDTO>();
	private static final long TWENTY_MIN = 1200000;
	private stocksUtil() {}
	public static stocksUtil getInstance() {
		return stockInfo;
	}
	/**
	 *
	 * @param symbol
	 * @return StocksDTO
	 * will return null if unable to retrieve information
	 */
	public RealQuoteDTO getStockPrice(String symbol) {
		RealQuoteDTO stock;
		long currentTime = (new Date()).getTime();
		// Check last updated and only pull stock on average every 20 minutes
		if (stocks.containsKey(symbol)) {
			stock = stocks.get(symbol);
			if(currentTime - stock.getLastUpdated() > TWENTY_MIN) {
				stock = fetchStockInfo(symbol, currentTime);
			}
		} else {
			stock = fetchStockInfo(symbol, currentTime);
		}
		return stock;
	}
	//This is synchronized so we only do one request at a time
	//If Yahoo doesn't return stock information we get info from the map in memory
	private synchronized RealQuoteDTO fetchStockInfo(String symbol, long time) {
		try {
			URL yahoofin = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=sl1d1t1c1ohgv&e=.csv");
			URLConnection yc = yahoofin.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				String[] yahooStockInfo = inputLine.split(",");
				RealQuoteDTO stockInfo = new RealQuoteDTO();
				stockInfo.setTicker(yahooStockInfo[0].replaceAll("\"", ""));
				stockInfo.setPrice(Float.valueOf(yahooStockInfo[1]));
				stockInfo.setChange(Float.valueOf(yahooStockInfo[4]));
				stockInfo.setLastUpdated(time);
				stocks.put(symbol, stockInfo);
				break;
			}
			in.close();
		} catch (Exception ex) {
			log.error("Unable to get stockinformation for: " + symbol + ex);
		}
		return stocks.get(symbol);
	}

	
	public static double getRoundedPrice(Double value) {
		return Math.round(value*100.0)/100.0;
	}
}
