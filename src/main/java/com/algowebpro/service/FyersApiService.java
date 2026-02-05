package com.algowebpro.service;

import com.tts.in.model.FyersClass;
import com.tts.in.model.StockHistoryModel;
import com.tts.in.utilities.Tuple;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FyersApiService {

	private final FyersClientProvider fyersClientProvider;

	public FyersApiService(FyersClientProvider fyersClientProvider) {
		this.fyersClientProvider = fyersClientProvider;
	}

	public JSONObject getProfile() {
		FyersClass fyers = fyersClientProvider.getClient();
		Tuple<JSONObject, JSONObject> response = fyers.GetProfile();

		if (response.Item2() == null) {
			return response.Item1();
		} else {
			throw new RuntimeException("Profile Error: " + response.Item2());
		}
	}

	public JSONObject getFunds() {
		FyersClass fyers = fyersClientProvider.getClient();
		Tuple<JSONObject, JSONObject> response = fyers.GetFunds();

		if (response.Item2() == null) {
			return response.Item1();
		} else {
			throw new RuntimeException("Funds Error: " + response.Item2());
		}
	}

	public JSONObject getHoldings() {
		FyersClass fyers = fyersClientProvider.getClient();
		Tuple<JSONObject, JSONObject> response = fyers.GetHoldings();

		if (response.Item2() == null) {
			return response.Item1();
		} else {
			throw new RuntimeException("Holdings Error: " + response.Item2());
		}
	}

	public JSONObject getTradeBook() {
		FyersClass fyers = fyersClientProvider.getClient();
		Tuple<JSONObject, JSONObject> response = fyers.GetTradeBook();

		if (response.Item2() == null) {
			return response.Item1();
		} else {
			throw new RuntimeException("TradeBook Error: " + response.Item2());
		}
	}

	public JSONObject getPositions() {
		FyersClass fyers = fyersClientProvider.getClient();
		Tuple<JSONObject, JSONObject> response = fyers.GetPositions();
		if (response.Item2() == null) {
			return response.Item1();
		} else {
			throw new RuntimeException("Positions Error: " + response.Item2());
		}
	}

	// ==================== Market Data ====================

	// @Cacheable(value = "marketStatus", unless = "#result.item2 != null")
	public Tuple<JSONObject, JSONObject> getMarketStatus() {
		FyersClass fyers = fyersClientProvider.getClient();
		Tuple<JSONObject, JSONObject> getMarketStatus = fyers.GetMarketStatus();
		return getMarketStatus;
	}

	public Tuple<JSONObject, JSONObject> getStockHistory(StockHistoryModel model) {
		FyersClass fyers = fyersClientProvider.getClient();
		return fyers.GetStockHistory(model);
	}

}
