package com.hypa.hypaship;

import android.content.Context;

import java.io.InputStream;

public class Util {

    public static String getManifestFromAssets(Context context)
    {
        try {
            //get the default moods from the config file or from the api
            InputStream is = context.getAssets().open("manifest.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, Constants.UTF8);
            return json;

        } catch (Exception ex) {

        }
        return null;
    }

}
