package com.algowebpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OhlcCandleDto {

	private long epoch;
	private String istTime;

	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	
	
	private Double rsi; // null for initial candles

}
