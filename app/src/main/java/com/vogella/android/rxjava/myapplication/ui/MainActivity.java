package com.vogella.android.rxjava.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.vogella.android.rxjava.myapplication.R;
import com.vogella.android.rxjava.myapplication.ui.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, ViewTodoItemActivity.class));
    }
}