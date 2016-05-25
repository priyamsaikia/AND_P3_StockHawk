package com.sam_chordas.android.stockhawk.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by PriyamSaikia on 25-05-2016.
 */
public class Parser {
    public Parser() {
    }

    public ArrayList<HistoryStockBean> getHistoryData(String response) {
        ArrayList<HistoryStockBean> arrayList = null;
        try {
            arrayList = new ArrayList<HistoryStockBean>();
            JSONObject mainObj = new JSONObject(response);
            JSONObject queryObj = mainObj.getJSONObject("query");
            JSONObject resultsObj = queryObj.getJSONObject("results");
            JSONArray quoteArray = resultsObj.getJSONArray("quote");

            for (int i = 0; i < quoteArray.length(); i++) {
                JSONObject itemObj = quoteArray.getJSONObject(i);
                HistoryStockBean historyStockBean = new HistoryStockBean();
                historyStockBean.setDate(itemObj.getString("Date"));
                historyStockBean.setAdjusted_close(itemObj.getString("Adj_Close"));

                arrayList.add(historyStockBean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
