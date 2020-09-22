package com.vogella.android.rxjava.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vogella.android.rxjava.myapplication.R;

public class EditTodoActivity extends AppCompatActivity {

    // intent values
    public static final String KEY_TODO_ITEM_TEXT = "todo-item-text";
    public static final String KEY_TODO_ITEM_POSITION = "todo-item-position";
    public static final int INTENT_CODE = 20;
    private Intent intent;

    // views
    EditText editTodoItem;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo_activty);
        getSupportActionBar().setTitle(getResources().getString(R.string.edit_item_action_bar_text));

        initUI();

        intent = getIntent();
        editTodoItem.setText(getIntent().getStringExtra(KEY_TODO_ITEM_TEXT));

        setUpOnclickListeners();
    }

    private void setUpOnclickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent();

                // put values
                newIntent.putExtra(KEY_TODO_ITEM_TEXT, editTodoItem.getText().toString());
                newIntent.putExtra(KEY_TODO_ITEM_POSITION, getIntent().getExtras().getInt(KEY_TODO_ITEM_POSITION));

                // set the result of the intent
                setResult(RESULT_OK, newIntent);

                // kill this activity
                finish();
            }
        });
    }

    private void initUI() {
        editTodoItem = findViewById(R.id.edit_todo_item);
        saveButton = findViewById(R.id.edit_save_button);
    }
}