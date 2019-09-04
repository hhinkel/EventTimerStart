package com.example.eventtimerstart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eventtimerstart.RiderContract.RiderEntry;

public class RiderDbHelper extends  SQLiteOpenHelper {

    public static final String LOG_TAG = RiderDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "rider.db";
    private static final int DATABASE_VERSION = 1;

    public RiderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_RIDER_TABLE =  "CREATE TABLE " + RiderEntry.TABLE_NAME + " ("
                + RiderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RiderEntry.COLUMN_RIDER_NUM + " INTEGER NOT NULL, "
                + RiderEntry.COLUMN_FENCE_NUM + "INTEGER NOT NULL, "
                + RiderEntry.COLUMN_RIDER_START + " INTEGER DEFAULT 0, "
                + RiderEntry.COLUMN_RIDER_FINISH + " INTEGER DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_RIDER_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

}
