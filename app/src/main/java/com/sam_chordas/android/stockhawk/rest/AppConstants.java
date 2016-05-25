package com.sam_chordas.android.stockhawk.rest;

/**
 * Created by PriyamSaikia on 24-05-2016.
 */
public class AppConstants {
    public static final String BASE_URL_HISTORY = "https://developer.yahoo.com/yql/console/?q";
    public static final String REQUEST_STOCK_HISTORY = "select * from yahoo.finance.historicaldata where symbol = \"YHOO\" and startDate = \"2009-09-11\" and endDate = \"2010-03-10\"";
    public static String URL_FINALISER_HISTORY = "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables." + "org%2Falltableswithkeys&callback=";
}
