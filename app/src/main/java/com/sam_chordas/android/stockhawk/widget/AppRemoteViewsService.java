package com.sam_chordas.android.stockhawk.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.ui.DetailActivity;

/**
 * Created by PriyamSaikia on 28-05-2016.
 */
public class AppRemoteViewsService extends RemoteViewsService {

    private static final String TAG = AppRemoteViewsService.class.getSimpleName();
    Cursor mCursor;

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
            // Refresh the cursor
            if (mCursor != null) {
                mCursor.close();
            }
            mCursor = mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI, null, null,
                    null, null);
        }

        @Override
        public void onDestroy() {
            if (mCursor != null) {
                mCursor.close();
            }
        }

        @Override
        public int getCount() {
            if (mCursor != null)
                return mCursor.getCount();
            else return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {

            RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget_list_item);
            if (mCursor != null) {
                if (mCursor.moveToPosition(position)) {
                    remoteViews.setTextViewText(R.id.tv_symbol,
                            mCursor.getString(mCursor.getColumnIndex(QuoteColumns.SYMBOL)));
                    remoteViews.setTextViewText(R.id.tv_bidprice,
                            mCursor.getString(mCursor.getColumnIndex(QuoteColumns.BIDPRICE)));
                    int resId = Utils.getResId(mCursor.getString(mCursor.getColumnIndex(QuoteColumns.ISUP)));
                    remoteViews.setImageViewResource(R.id.imv_widget, resId);
                }
            }

            Intent configIntent = new Intent(AppRemoteViewsService.this, DetailActivity.class);

            PendingIntent configPendingIntent = PendingIntent.getActivity(AppRemoteViewsService.this, 0, configIntent, 0);

            remoteViews.setOnClickPendingIntent(R.id.ll_item_widget, configPendingIntent);
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
