package com.albums.details.sample.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.albums.details.sample.R;

public class SharedPrefHelper {

    public static void saveBool(Context context,String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(context.getString(R.string.app_name), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBool(Context context,String key, boolean defaultValue){
        SharedPreferences sp = context.getSharedPreferences(
                context.getString(R.string.app_name), Activity.MODE_PRIVATE);


        return sp.getBoolean(key, defaultValue);
    }

}
