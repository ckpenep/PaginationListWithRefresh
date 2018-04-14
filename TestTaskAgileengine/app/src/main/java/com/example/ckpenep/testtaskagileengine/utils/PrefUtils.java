package com.example.ckpenep.testtaskagileengine.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.ckpenep.testtaskagileengine.App;

/**
 * Created by ckpenep on 26.03.2018.
 */

public class PrefUtils {
    private static final String PREF_NAME = "stackoverflow";

    private static SharedPreferences getPrefs() {
        return App.getAppComponent().getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor() {
        return getPrefs().edit();
    }

    public static String getPreference(@NonNull String key, @NonNull String value)
    {
        return getPrefs().getString(key, value);
    }

    public static void setPreference(@NonNull String key, @NonNull String value)
    {
        getEditor().putString(key, value).commit();
    }
}
