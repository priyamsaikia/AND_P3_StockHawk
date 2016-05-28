package com.sam_chordas.android.stockhawk.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.rest.AppConstants;

/**
 * Created by PriyamSaikia on 28-05-2016.
 */
public class AppRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyRemoteViewsFactory(this, intent);
    }

    private class MyRemoteViewsFactory implements RemoteViewsFactory {
        Context mContext;
        int mAppWidgetId;
        Cursor mCursor;

        public MyRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI, null, null, null, null).getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget_list_item);
            Cursor cursor = mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI, null, null, null, null);
            String symbol = cursor.getString(cursor.getColumnIndex(QuoteColumns.SYMBOL));
            String bid = cursor.getString(cursor.getColumnIndex(QuoteColumns.BIDPRICE));
            if (cursor.getString(cursor.getColumnIndex(QuoteColumns.ISUP)).equals("1")) {
                remoteViews.setImageViewResource(R.id.widget_up_or_down, R.mipmap.ic_green);
            } else {
                remoteViews.setImageViewResource(R.id.widget_up_or_down, R.mipmap.ic_red);
            }
            remoteViews.setTextViewText(R.id.stock_symbol, symbol);
            remoteViews.setTextViewText(R.id.widget_bid_price, bid);

            Intent intentItem = new Intent();
            intentItem.putExtra(AppConstants.BUNDLE_STOCK, symbol);
            remoteViews.setOnClickFillInIntent(R.id.stock_symbol, intentItem);
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
