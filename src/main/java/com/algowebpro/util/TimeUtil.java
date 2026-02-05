package com.algowebpro.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
	private static final ZoneId IST_ZONE = ZoneId.of("Asia/Kolkata");
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static String epochToIst(long epochSeconds) {
		return Instant.ofEpochSecond(epochSeconds).atZone(IST_ZONE).format(FORMATTER);
	}
}
