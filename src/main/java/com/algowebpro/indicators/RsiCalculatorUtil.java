package com.algowebpro.indicators;

import java.util.List;

import com.algowebpro.dto.OhlcCandleDto;

public class RsiCalculatorUtil {

	private RsiCalculatorUtil() {
	}

	/**
	 * Adds RSI to OHLC candles using Wilder's method
	 */
	public static void addRsi(List<OhlcCandleDto> candles, int length) {

		if (candles.size() <= length) {
			return;
		}

		double gainSum = 0;
		double lossSum = 0;

		// Step 1: initial average gain/loss
		for (int i = 1; i <= length; i++) {
			double change = candles.get(i).getClose() - candles.get(i - 1).getClose();
			if (change > 0) {
				gainSum += change;
			} else {
				lossSum += Math.abs(change);
			}
		}

		double avgGain = gainSum / length;
		double avgLoss = lossSum / length;

		// First RSI value
		candles.get(length).setRsi(calculateRsi(avgGain, avgLoss));

		// Step 2: Wilder smoothing
		for (int i = length + 1; i < candles.size(); i++) {

			double change = candles.get(i).getClose() - candles.get(i - 1).getClose();
			double gain = Math.max(change, 0);
			double loss = Math.max(-change, 0);

			avgGain = ((avgGain * (length - 1)) + gain) / length;
			avgLoss = ((avgLoss * (length - 1)) + loss) / length;

			candles.get(i).setRsi(calculateRsi(avgGain, avgLoss));
		}
	}

	private static double calculateRsi(double avgGain, double avgLoss) {
		if (avgLoss == 0) {
			return 100.0;
		}
		double rs = avgGain / avgLoss;
		return 100 - (100 / (1 + rs));
	}

}
