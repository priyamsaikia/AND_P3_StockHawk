package com.sam_chordas.android.stockhawk.service;

import java.io.Serializable;

/**
 * Created by PriyamSaikia on 25-05-2016.
 */
public class HistoryStockBean implements Serializable {
    public HistoryStockBean(){

    }

    String date;

    public String getAdjusted_close() {
        return adjusted_close;
    }

    public void setAdjusted_close(String adjusted_close) {
        this.adjusted_close = adjusted_close;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String adjusted_close;
}
