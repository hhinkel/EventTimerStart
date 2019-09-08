package com.example.eventtimerstart;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ExistingRiderCursorAdaptor extends CursorAdapter {

    public ExistingRiderCursorAdaptor (Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        EditText numberEditText = (EditText) view.findViewById(R.id.edit_rider_number);

        int numberColumnIndex = cursor.getColumnIndex(RiderContract.RiderEntry.COLUMN_RIDER_NUM);

        String riderNumber = cursor.getString(numberColumnIndex);

        numberEditText.setText(riderNumber);

    }

}
