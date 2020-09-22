package com.vogella.android.rxjava.myapplication.theme;

import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeChangeHelper {

    public static final String SAVEDTHEME = "saved-theme";

    public static void setAppTheme( ThemeType themePref) {
        switch (themePref) {
            case LightMode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case DarkMode:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                }
                break;
        }
    }
}