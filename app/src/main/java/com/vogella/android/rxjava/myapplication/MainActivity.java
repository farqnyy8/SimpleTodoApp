package com.vogella.android.rxjava.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // constants
    final String TAG = "MainActivity";
    final private String READDATAERROR = "Error Reading Data";
    final private String WRITEDATAERROR = "Error Writing Data";
    final private String UNKNOWNCALLTOONACTIVITYRESULT = "UnKnown call to onActivityResult";

    // data holder
    List<String> items;

    // views
    Button addButton;
    EditText addNewTodoEditText;

    // recyclerviews, adapters, and adapter listeners
    RecyclerView listOfTodos;
    TodoItemAdapter todoItemAdapter;
    TodoItemAdapter.OnLongClickListener onLongClickListener;
    TodoItemAdapter.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setUpOnclickListeners();
        loadData();

        todoItemAdapter = new TodoItemAdapter(items, onLongClickListener, onClickListener);
        listOfTodos.setAdapter(todoItemAdapter);
        listOfTodos.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpOnclickListeners() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = addNewTodoEditText.getText().toString();

                // add new item to model
                items.add(newTodo);

                // notify adapter that an item is inserted
                todoItemAdapter.notifyItemInserted(items.size() - 1);

                // clear the edit text and notify user
                addNewTodoEditText.setText("");
                makeToast("New Todo Item is added");

                // save changes
                storeData();
            }
        });

        onLongClickListener = new TodoItemAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                String itemRemoved = items.get(position);
                items.remove(position);
                todoItemAdapter.notifyItemRemoved(position);
                makeToast(itemRemoved + " was removed");

                // save changes
                storeData();
            }
        };

        onClickListener = new TodoItemAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                // create intent
                Intent editActivityIntent = new Intent(MainActivity.this, EditTodoActivity.class);

                // put required values
                editActivityIntent.putExtra(EditTodoActivity.KEY_TODO_ITEM_POSITION, position);
                editActivityIntent.putExtra(EditTodoActivity.KEY_TODO_ITEM_TEXT, items.get(position));

                // display the activity
                startActivityForResult(editActivityIntent, EditTodoActivity.INTENT_CODE);
            }
        };
    }

    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void initUI() {
        listOfTodos = findViewById(R.id.listOfTodos);
        addButton = findViewById(R.id.add_todo_item_btn);
        addNewTodoEditText = findViewById(R.id.add_todo_item_edit_text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == EditTodoActivity.INTENT_CODE) {
                // get the return values
                String newTodoText = data.getStringExtra(EditTodoActivity.KEY_TODO_ITEM_TEXT);
                int changePosition = data.getExtras().getInt(EditTodoActivity.KEY_TODO_ITEM_POSITION);

                // notify the data model
                items.set(changePosition, newTodoText);

                // notify the view
                todoItemAdapter.notifyItemChanged(changePosition);

                // save items
                storeData();

                makeToast("Todo item updated successfully");
            }
            else
            {
                Log.w(TAG, UNKNOWNCALLTOONACTIVITYRESULT);
            }
        }
    }

    private File getDataFile() {
        return new File(getFilesDir(), "data.txt");
    }

    // loads data
    private void loadData() {
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e(TAG, READDATAERROR);
            items = new ArrayList<>();
        }
    }

    // stores data
    private void storeData() {
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e(TAG, WRITEDATAERROR);
        }
    }
}