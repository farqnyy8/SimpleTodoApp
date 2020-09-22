package com.vogella.android.rxjava.myapplication.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vogella.android.rxjava.myapplication.R;
import com.vogella.android.rxjava.myapplication.theme.ThemeChangeHelper;
import com.vogella.android.rxjava.myapplication.theme.ThemeType;

public class SettingsFragment extends Fragment {

    private RelativeLayout theme_light_mode;
    private ImageView theme_light_mode_icon;
    private RelativeLayout theme_dark_mode;
    private ImageView theme_dark_mode_icon;
    private RelativeLayout theme_default_mode;
    private ImageView theme_default_mode_icon;

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUi(view);
        setOnClickListeners();
    }

    private void initUi(View view) {
        theme_light_mode = view.findViewById(R.id.light_mode);
        theme_dark_mode = view.findViewById(R.id.dark_mode);
        theme_default_mode = view.findViewById(R.id.default_mode);

        theme_light_mode_icon = view.findViewById(R.id.light_mode_icon);
        theme_dark_mode_icon = view.findViewById(R.id.dark_mode_icon);
        theme_default_mode_icon = view.findViewById(R.id.default_mode_icon);
    }

    private void setOnClickListeners() {
        theme_light_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearThemeIcons();
                ThemeChangeHelper.setAppTheme(ThemeType.LightMode);
                changeThemeSetIcon(ThemeType.LightMode);
                saveTheme(ThemeType.LightMode);
            }
        });
        theme_dark_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearThemeIcons();
                ThemeChangeHelper.setAppTheme(ThemeType.DarkMode);
                changeThemeSetIcon(ThemeType.DarkMode);
                saveTheme(ThemeType.DarkMode);
            }
        });
        theme_default_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearThemeIcons();
                ThemeChangeHelper.setAppTheme(ThemeType.DefaultMode);
                changeThemeSetIcon(ThemeType.DefaultMode);
                saveTheme(ThemeType.DefaultMode);
            }
        });
    }

    private void clearThemeIcons() {
        theme_light_mode_icon.setVisibility(View.INVISIBLE);
        theme_dark_mode_icon.setVisibility(View.INVISIBLE);
        theme_default_mode_icon.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpWithThemeChanges();
    }

    private void setUpWithThemeChanges() {
        changeThemeSetIcon(
                ThemeType.getThemeType(
                        sharedPreferences.getString(
                                ThemeChangeHelper.SAVEDTHEME,
                                ThemeType.DefaultMode.toString())
                )
        );
    }

    private void changeThemeSetIcon(ThemeType appTheme) {
        switch (appTheme) {
            case LightMode:
                theme_light_mode_icon.setVisibility(View.VISIBLE);
                break;
            case DarkMode:
                theme_dark_mode_icon.setVisibility(View.VISIBLE);
                break;
            default:
                theme_default_mode_icon.setVisibility(View.VISIBLE);
        }
    }

    private void saveTheme(ThemeType appTheme) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ThemeChangeHelper.SAVEDTHEME, appTheme.toString());
        editor.apply();
    }
}