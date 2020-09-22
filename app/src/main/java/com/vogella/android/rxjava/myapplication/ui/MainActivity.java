package com.vogella.android.rxjava.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vogella.android.rxjava.myapplication.R;
import com.vogella.android.rxjava.myapplication.ui.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    Button go_to_settings_btn;
    Button view_todo_items_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        setUpOnClickListeners();
    }

    private void initUi() {
        go_to_settings_btn = findViewById(R.id.go_to_settings_btn);
        view_todo_items_btn = findViewById(R.id.view_todo_items_btn);
    }

    private void setUpOnClickListeners() {
        view_todo_items_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewTodoItemActivity.class));
            }
        });
        go_to_settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }
}