package com.example.eventtimerstart;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RiderCursorAdapter extends CursorAdapter {

    RiderCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView numberTextView = view.findViewById(R.id.number);
        TextView divisionTextView = view.findViewById(R.id.division);
        TextView editTextView = view.findViewById(R.id.edited);
        TextView summaryTextView = view.findViewById(R.id.summary);

        int numberColumnIndex = cursor.getColumnIndex(RiderContract.RiderEntry.COLUMN_RIDER_NUM);
        int divisionColumnIndex = cursor.getColumnIndex(RiderContract.RiderEntry.COLUMN_DIVISION);
        int startColumnIndex = cursor.getColumnIndex(RiderContract.RiderEntry.COLUMN_RIDER_START);
        int editedColumnIndex = cursor.getColumnIndex(RiderContract.RiderEntry.COLUMN_EDIT);

        String riderNumber = cursor.getString(numberColumnIndex);
        String division = cursor.getString(divisionColumnIndex);
        String editRaw = cursor.getString(editedColumnIndex);
        long startTimeRaw = cursor.getLong(startColumnIndex);
        String startTime = "Start Time: " + formatStartTime(startTimeRaw);

        numberTextView.setText(riderNumber);
        divisionTextView.setText(division);
        if(editRaw != null)
            editTextView.setText("Edited");
        summaryTextView.setText(startTime);
    }

    private String formatStartTime(long timeRaw) {
        Timestamp ts = new Timestamp(timeRaw);
        Date time = new Date(ts.getTime());
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss:SS", Locale.getDefault());
        return format.format(time);
    }
}
