package com.sam_chordas.android.stockhawk.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.rest.AppConstants;
import com.sam_chordas.android.stockhawk.service.HistoryStockBean;
import com.sam_chordas.android.stockhawk.service.MyConnection;
import com.sam_chordas.android.stockhawk.service.Parser;
import com.sam_chordas.android.stockhawk.service.VolleyRequest;

import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PriyamSaikia on 21-05-2016.
 */
public class DetailActivity extends AppCompatActivity implements MyConnection.IMyConnection {
    private static final String TAG = DetailActivity.class.getSimpleName();
    @Bind(R.id.linechart)
    LineChart lineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        String url = String.format(AppConstants.REQUEST_STOCK_HISTORY,getIntent().getStringExtra(AppConstants.BUNDLE_STOCK));

        VolleyRequest.sendRequest(this, AppConstants.BASE_URL_HISTORY +
                        URLEncoder.encode(url)
                        + AppConstants.URL_FINALISER_HISTORY
                , this, 0);
    }

    @Override
    public void onSuccess(String response, int requestId) {
        Parser parser = new Parser();
        ArrayList<HistoryStockBean> arrayList = parser.getHistoryData(response);
        setUpGraph(arrayList);
    }

    private void setUpGraph(ArrayList<HistoryStockBean> arrayList) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++)
            entries.add(new Entry(Float.valueOf(arrayList.get(i).getAdjusted_close()), i));
        Log.d(TAG, "entries added");

        LineDataSet dataset = new LineDataSet(entries, "History of finance");

        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i < arrayList.size(); i++)
            labels.add(arrayList.get(i).getDate());

        LineData data = new LineData(labels, dataset);
        lineChart.setData(data); // set the data and list of lables into chart
        lineChart.invalidate();
    }

    @Override
    public void onFailure(String error, int requestId) {

    }
}
