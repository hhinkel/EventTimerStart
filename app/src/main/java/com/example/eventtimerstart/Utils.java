package com.example.eventtimerstart;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static String loadJSONSetupData(Context context) {
        String json;
        try {
            InputStream inputStream = context.getAssets().open("setup.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException error) {
            error.printStackTrace();
            return null;
        }

        Log.e("data", json);
        return json;
    }
}
