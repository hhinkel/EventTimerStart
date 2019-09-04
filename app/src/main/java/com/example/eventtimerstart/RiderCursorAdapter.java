package com.example.eventtimerstart;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class RiderCursorAdapter extends CursorAdapter {

    public RiderCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView numberTextView = (TextView) view.findViewById(R.id.number);
        TextView summaryTextView = (TextView) view.findViewById(R.id.summary);

        int numberColumnIndex = cursor.getColumnIndex(RiderContract.RiderEntry.COLUMN_RIDER_NUM);
        int startColumnIndex = cursor.getColumnIndex(RiderContract.RiderEntry.COLUMN_RIDER_START);

        String riderNumber = cursor.getString(numberColumnIndex);
        String startTime = cursor.getString(startColumnIndex);

        numberTextView.setText(riderNumber);
        summaryTextView.setText(startTime);
    }
}
