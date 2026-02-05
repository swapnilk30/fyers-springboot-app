package com.algowebpro.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StockHistoryRequest {

	@NotBlank(message = "Symbol is required")
	private String symbol;

	@NotBlank(message = "Resolution is required")
	private String resolution;

	private String dateFormat = "1";

	@NotBlank(message = "Range from is required")
	private String rangeFrom;

	@NotBlank(message = "Range to is required")
	private String rangeTo;

	@Min(value = 0, message = "Cont flag must be 0 or 1")
	@Max(value = 1, message = "Cont flag must be 0 or 1")
	private int contFlag = 1;

}
