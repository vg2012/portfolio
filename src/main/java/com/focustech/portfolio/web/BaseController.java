package com.focustech.portfolio.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController {

	private HashMap _aHashMap = new HashMap();

	protected Map _get_aHashMap(String trackingKey) {
		if (trackingKey != null) {
			HashMap theMap = (HashMap) _aHashMap.get(trackingKey);
			return theMap;
		}
		return null;
	}

	protected HashMap _createPaginationBlocks(List<Serializable> dataSet,
			int totalNumRecsPerPage, String trackingKey) {
		if (dataSet.size() > totalNumRecsPerPage) {
			BigDecimal d1 = new BigDecimal(dataSet.size());
			BigDecimal d2 = new BigDecimal(totalNumRecsPerPage);
			int d = d1.divide(d2, BigDecimal.ROUND_UP).intValue();
			int start = 0;
			int next = totalNumRecsPerPage;
			System.out.println("total size " + dataSet.size());
			System.out.println("total pages " + d);
			HashMap<String, List<Serializable>> m = new HashMap<String, List<Serializable>>();
			for (int i = 0; i <= (d - 1); i++) {
				if (i == (d - 1)) {
					start += 1;
					next = dataSet.size();
				}
				m.put(Integer.toString(i + 1), dataSet.subList(start, next));
				if (i != (d - 1)) {
					start += totalNumRecsPerPage;
					next += totalNumRecsPerPage;
				}
			}

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("TRACKING_KEY", trackingKey);
			map.put("TOTAL_PAGES", Integer.toString(d));
			_aHashMap.put(trackingKey, m);
			return map;
		}
		return null;
	}

	protected String _genTrackingKey() {
		String currentUserToken = String.valueOf(Math.random());
		if (currentUserToken != null) {
			return currentUserToken;
		}
		return "NO_TOKEN";
	}

	protected void _cleanPaginationSessionToken(String token) {
		String currentUserToken = (String) _aHashMap.get(token);
		if (currentUserToken != null)
			_aHashMap.remove(currentUserToken);
	}

	protected void _clearPaginationCache() {
		_aHashMap.clear();
	}

}
