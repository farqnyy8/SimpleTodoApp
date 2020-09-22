package com.vogella.android.rxjava.myapplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vogella.android.rxjava.myapplication.theme.ThemeChangeHelper;
import com.vogella.android.rxjava.myapplication.theme.ThemeType;

public class SimpleTodoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initAppTheme();
    }

    private void initAppTheme() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        ThemeType appTheme = ThemeType.getThemeType(
                sharedPreferences.getString(
                        ThemeChangeHelper.SAVEDTHEME,
                        ThemeType.DefaultMode.toString()
                )
        );
        ThemeChangeHelper.setAppTheme(appTheme);
    }
}
