package com.algowebpro.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.algowebpro.dto.OhlcCandleDto;
import com.algowebpro.indicators.RsiCalculatorUtil;

public class CandleConverterUtil {
	
	private CandleConverterUtil() {
	}

	public static List<OhlcCandleDto> toOhlcWithRsi(JSONArray candles, int rsiLength) {

		List<OhlcCandleDto> list = new ArrayList<>();

		for (int i = 0; i < candles.length(); i++) {

			JSONArray c = candles.getJSONArray(i);

			long epoch = c.getLong(0);
			String istTime = TimeUtil.epochToIst(epoch);

			list.add(new OhlcCandleDto(epoch, istTime, c.getDouble(1), c.getDouble(2), c.getDouble(3), c.getDouble(4),
					c.getLong(5), null // RSI calculated later
			));
		}

		//Add RSI
		RsiCalculatorUtil.addRsi(list, rsiLength);

		return list;
	}

}
