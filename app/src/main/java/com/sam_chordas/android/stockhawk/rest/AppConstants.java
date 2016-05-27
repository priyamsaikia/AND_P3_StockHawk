package com.sam_chordas.android.stockhawk.rest;

/**
 * Created by PriyamSaikia on 24-05-2016.
 */
public class AppConstants {
    public static final String BASE_URL_HISTORY = "https://query.yahooapis.com/v1/public/yql?q=";
    public static final String REQUEST_STOCK_HISTORY = "select * from yahoo.finance.historicaldata where symbol = \"%s\" " +
            "and startDate = \"2016-04-01\" and endDate = \"2016-05-01\"";
    public static final String BUNDLE_STOCK = "selected_stock";
    public static String URL_FINALISER_HISTORY = "&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables." + "org%2Falltableswithkeys&callback=";
}
