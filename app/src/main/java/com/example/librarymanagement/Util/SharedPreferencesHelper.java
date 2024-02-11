package com.example.librarymanagement.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.librarymanagement.ModelClass.User;

public class SharedPreferencesHelper
{
    private static SharedPreferencesHelper instance;
    private final SharedPreferences preferences;

    private static final String USER_ID = "user id";
    private static final String LOGIN_STATUS = "login_status";

    private SharedPreferencesHelper(Context context)
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferencesHelper getInstance(Context context)
    {
        if (instance == null) {
            instance = new SharedPreferencesHelper(context);
        }
        return instance;
    }

    public void setUserId(String userId) {
        preferences.edit().putString(USER_ID, userId).apply();
    }

    public String getUserId() {
        return preferences.getString(USER_ID, "");
    }

    public void setLoginStatus(Boolean s) {
        preferences.edit().putBoolean(LOGIN_STATUS, s).apply();
    }

    public Boolean getLoginStatus() {
        return preferences.getBoolean(LOGIN_STATUS, false);
    }

}
