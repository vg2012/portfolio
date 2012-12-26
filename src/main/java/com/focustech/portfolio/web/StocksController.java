package com.focustech.portfolio.web;

import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.focustech.portfolio.domain.Stocks;
import com.focustech.portfolio.dto.CommonPaginationCriteriaDTO;
import com.focustech.portfolio.dto.RealQuoteDTO;
import com.focustech.portfolio.dto.StocksDTO;
import com.focustech.portfolio.service.StocksService;
import com.focustech.portfolio.util.stocksUtil;

@Controller("StocksController")
public class StocksController extends BaseController {

	@Autowired
	private StocksService stocksService;

	@RequestMapping(value="/stockDashboard", method = RequestMethod.GET)
	public ModelAndView showDashboard(ModelMap map,Principal principal){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stocks/stocks-dashboard-view");
		String name = principal.getName();
		map.addAttribute("username", name);
		return mav;
	}

	@RequestMapping(value="/holdings", method = RequestMethod.POST)
	public ModelAndView stocksLoad(ModelMap map){
		ModelAndView mav = new ModelAndView();
		_clearPaginationCache();
		String trackingKey = _genTrackingKey();
		_cleanPaginationSessionToken( trackingKey );

		List<Stocks> stockList =  stocksService.findAllStocks(-1, -1);
		List<StocksDTO> stockDTOList = new ArrayList<StocksDTO>();

		String pattern = "MM/dd/yyyy";

		if (null != stockList && !stockList.isEmpty()) {
			for (Stocks stock : stockList) {
				StocksDTO stocksDTO = new StocksDTO();
				stocksDTO.setStockId(stock.getStockId());
				stocksDTO.setPricePaid(stocksUtil.getRoundedPrice(stock.getPrice()));
				stocksDTO.setTicker(stock.getTicker());
				stocksDTO.setQuantity(stock.getQuantity());
				stocksDTO.setPurchaseDate(stock.getPurchaseDate());
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				stocksDTO.setPurchaseDateDisply(format.format(stock.getPurchaseDate()));
				Double amount = new Double(stock.getPrice() * stock.getQuantity());
				stocksDTO.setAmount(stocksUtil.getRoundedPrice(amount));
				//Fetch the current price of the stock
				RealQuoteDTO realQuoteDTO = stocksUtil.getInstance().getStockPrice(stock.getTicker());
				Double realPrice = null != realQuoteDTO ? realQuoteDTO.getPrice() :stock.getPrice() ;
				Double realTimeAmout = stock.getQuantity() * realPrice;
				Double gainOrLoss = realTimeAmout - amount; 
				if (gainOrLoss > 0) {
					stocksDTO.setProfitIndicator("+");
				}
				stocksDTO.setGainLoss(stocksUtil.getRoundedPrice(gainOrLoss));
				stockDTOList.add(stocksDTO);
			}
		}
		List<Serializable> sData = new ArrayList<Serializable>(stockDTOList);

		HashMap hm = _createPaginationBlocks( sData, 6, trackingKey );
		Map aPageData = _get_aHashMap( trackingKey );

		Map<String,List<StocksDTO>> pageBlock=(Map) aPageData;
		List<StocksDTO> pagedList = new ArrayList<StocksDTO>();
		if(pageBlock != null){
			pagedList =(List<StocksDTO>) pageBlock.get("1");
		}

		String pageChunks = "0";

		if (null == hm) {
			pagedList = stockDTOList;
		} else {
			pageChunks =  (String) hm.get("TOTAL_PAGES");
		}

		CommonPaginationCriteriaDTO cpcDTO = new CommonPaginationCriteriaDTO();
		cpcDTO.setCurrrentIdx("1");
		cpcDTO.setNextIdx("2");
		cpcDTO.setNumOfPageChunks(pageChunks);
		cpcDTO.setPreviousIdx("-1");
		cpcDTO.setTrackingToken(trackingKey);

		map.addAttribute("pagingCriteria", cpcDTO);
		map.addAttribute("stocksList", pagedList);
		mav.setViewName("stocks/holding-grid-view");
		return mav;
	}

	@RequestMapping(value = "addStock", method = RequestMethod.POST)
	public ModelAndView stockDetailAdd(@Valid @ModelAttribute(value="stocksDTO") StocksDTO stocksDTO,  
			BindingResult result, ModelMap map)  {
		ValidationUtils.rejectIfEmpty(result, "ticker", "Ticker can not be empty.");
		ValidationUtils.rejectIfEmpty(result, "pricePaid", "pricePaid.required");
		ValidationUtils.rejectIfEmpty(result, "quantity", "Quantity not be empty");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stocks/stocks-dashboard-view");
		String returnValue = null;
		Stocks stocks = new Stocks();
		if(result.hasErrors()){
			returnValue =  "FAILURE";
		} else {
		Stocks returnStockAfterSave = null;
		stocks.setPrice(stocksDTO.getPricePaid());
		stocks.setQuantity(stocksDTO.getQuantity());
		stocks.setTicker(stocksDTO.getTicker());
		java.util.Date utilDate = new Date();
		// Convert it to java.sql.Date
		Date purchaseDate = new Date(utilDate.getTime());
		stocks.setPurchaseDate(purchaseDate);
		try {
			Stocks foundStock = stocksService.findStockByTicker(stocksDTO.getTicker());
			if(null != foundStock){
				Integer stocksOwned = foundStock.getQuantity();
				Double pricePaid = foundStock.getPrice();
				Integer newQuantity = stocksDTO.getQuantity() + stocksOwned;
				Double averagePricePaid = (stocksDTO.getPricePaid() + pricePaid)/2;
				foundStock.setQuantity(newQuantity);
				foundStock.setPrice(averagePricePaid);
				foundStock.setPurchaseDate(purchaseDate);
				stocksService.saveStocks(foundStock);
			} else {
				stocksService.saveStocks(stocks);
			}
			returnStockAfterSave = stocksService.findStockByTicker(stocksDTO.getTicker());

			if (null != returnStockAfterSave) {
				returnValue = "SUCCESS";
			} 

		} catch (Exception e) {
			// TODO Logging can be added
		}
		
		}
		map.addAttribute("returnValue", returnValue); // Can be used for alerts
		return mav;
	}


	@RequestMapping(value = "/holdingsPaged", method = RequestMethod.POST)
	public ModelAndView pageResults(@ModelAttribute("pageCriteria") CommonPaginationCriteriaDTO pageCriteria,  
			@RequestParam("source") String source,
			ModelMap map) {

		ModelAndView mav = new ModelAndView();

		CommonPaginationCriteriaDTO cpcDTO = new CommonPaginationCriteriaDTO();
		cpcDTO.setCurrrentIdx(pageCriteria.getCurrrentIdx());
		cpcDTO.setNextIdx(pageCriteria.getNextIdx());
		cpcDTO.setNumOfPageChunks(pageCriteria.getNumOfPageChunks());
		cpcDTO.setPreviousIdx(pageCriteria.getPreviousIdx());
		cpcDTO.setTrackingToken(pageCriteria.getTrackingToken());
		cpcDTO.setSource(pageCriteria.getSource());

		Map aPageData = _get_aHashMap( pageCriteria.getTrackingToken() );
		Map<String,List<StocksDTO>> pageBlock=(Map) aPageData;
		List<StocksDTO> pagedList = new ArrayList<StocksDTO>();
		if(pageBlock != null){
			pagedList =(List<StocksDTO>) pageBlock.get(pageCriteria.getNextIdx());
		}
		map.addAttribute("pagingCriteria", cpcDTO);
		map.addAttribute("stocksList", pagedList);
		mav.setViewName("stocks/holding-grid-view");
		return mav;
	}

	@RequestMapping(value = "/validateTicker", method = RequestMethod.GET)
	public @ResponseBody
	String validateOrgName(@RequestParam("ticker") String ticker)

	{
		if (null == ticker) {
			return "<font color='red'>Ticker is required</font>";
		} else {
			RealQuoteDTO realQuoteDTO = stocksUtil.getInstance().getStockPrice(ticker);

			if (null != realQuoteDTO && null != realQuoteDTO.getTicker()) {
				return "";
			} else {
				return "<font color='red'>Invalid ticker, please try again</font>";
			}
		}
	}

}
