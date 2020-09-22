package com.vogella.android.rxjava.myapplication.theme;

public enum ThemeType {

    LightMode,
    DarkMode,
    DefaultMode;

    public static ThemeType getThemeType(String themeName){
        return DefaultMode;
    }
}
