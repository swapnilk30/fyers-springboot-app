package com.algowebpro.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algowebpro.dto.ApiResponse;
import com.algowebpro.dto.OhlcCandleDto;
import com.algowebpro.dto.StockHistoryRequest;
import com.algowebpro.service.FyersApiService;
import com.algowebpro.util.CandleConverterUtil;
import com.algowebpro.util.TimeUtil;
import com.tts.in.model.StockHistoryModel;
import com.tts.in.utilities.Tuple;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
@Tag(name = "Market Data", description = "Market data APIs")
public class MarketDataController {

	private final FyersApiService fyersApiService;

	@GetMapping("/status")
	@Operation(summary = "Get market status")
	public ResponseEntity<ApiResponse> getMarketStatus() {
		Tuple<JSONObject, JSONObject> result = fyersApiService.getMarketStatus();
		return buildResponse(result, "Market status retrieved successfully");
	}

	@PostMapping("/history")
	@Operation(summary = "Get stock history")
	public ResponseEntity<ApiResponse> getStockHistory(@Valid @RequestBody StockHistoryRequest request) {
		StockHistoryModel model = new StockHistoryModel();
		model.Symbol = request.getSymbol();
		model.Resolution = request.getResolution();
		model.DateFormat = request.getDateFormat();
		model.RangeFrom = request.getRangeFrom();
		model.RangeTo = request.getRangeTo();
		model.ContFlag = request.getContFlag();

		Tuple<JSONObject, JSONObject> result = fyersApiService.getStockHistory(model);
		return buildResponse(result, "Stock history retrieved successfully");
	}

	private ResponseEntity<ApiResponse> buildResponse(Tuple<JSONObject, JSONObject> result, String successMessage) {
		if (result.Item2() == null) {
			JSONObject data = result.Item1();

			if (data.has("candles")) {
				JSONArray candles = data.getJSONArray("candles");

	            // RSI length = 14 (standard)
	            List<OhlcCandleDto> ohlc =
	                    CandleConverterUtil.toOhlcWithRsi(candles, 14);

	            data.remove("candles");
	            data.put("ohlc", ohlc);
			}
			return ResponseEntity.ok(ApiResponse.success(successMessage, result.Item1().toMap()));
		} else {
			return ResponseEntity.badRequest()
					.body(ApiResponse.error(result.Item2().optString("message", "Operation failed")));
		}
	}

	private void addIstToCandles(JSONObject data) {

		JSONArray candles = data.getJSONArray("candles");
		JSONArray updatedCandles = new JSONArray();

		for (int i = 0; i < candles.length(); i++) {

			JSONArray candle = candles.getJSONArray(i);

			long epoch = candle.getLong(0);
			String istTime = TimeUtil.epochToIst(epoch);

			JSONArray newCandle = new JSONArray();

			newCandle.put(epoch); // keep epoch
			newCandle.put(istTime); // add IST time
			newCandle.put(candle.getDouble(1)); // Open
			newCandle.put(candle.getDouble(2)); // High
			newCandle.put(candle.getDouble(3)); // Low
			newCandle.put(candle.getDouble(4)); // Close
			newCandle.put(candle.getLong(5)); // Volume

			updatedCandles.put(newCandle);
		}

		data.put("candles", updatedCandles);
	}

}
