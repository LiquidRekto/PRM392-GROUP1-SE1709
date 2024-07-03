package com.example.fitapp.util;

import android.content.SharedPreferences;

public class SharedPrefUtils {
    public static void attachKey(SharedPreferences prefs, String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void detachKey(SharedPreferences prefs, String key) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }

    public static String retrieveKeyValue(SharedPreferences prefs, String key) {
        return prefs.getString(key, "");
    }
}
