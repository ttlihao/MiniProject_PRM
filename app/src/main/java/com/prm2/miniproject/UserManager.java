package com.prm2.miniproject;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
    }

    public static String getUsername(Context context) {
        return getSharedPreferences(context).getString("username", null);
    }

    public static String getPassword(Context context) {
        return getSharedPreferences(context).getString("password", null);
    }
}
