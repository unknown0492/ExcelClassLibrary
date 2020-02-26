package com.excel.excelclasslibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UtilSharedPreferences {
    public static SharedPreferences createSharedPreference(Context ct, String name) {
        return ct.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static void editSharedPreference(SharedPreferences spfs, String key, String value) {
        Editor spe = spfs.edit();
        spe.putString(key, value);
        spe.commit();
    }

    public static Object getSharedPreference(SharedPreferences spfs, String name, String default_value) {
        return spfs.getString(name, default_value);
    }
}
