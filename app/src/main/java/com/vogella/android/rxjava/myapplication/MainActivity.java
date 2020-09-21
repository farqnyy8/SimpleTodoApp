package com.vogella.android.rxjava.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    final String READDATAERROR = "Error Reading Data";
    final String WRITEDATAERROR = "Error Writing Data";

    // data holder
    List<String> items;

    // views
    Button addButton;
    EditText addNewTodoEditText;

    // recyclerviews, adapters, and adapter listeners
    RecyclerView listOfTodos;
    TodoItemAdapter todoItemAdapter;
    TodoItemAdapter.OnLongClickListener onLongClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setUpOnclickListeners();
        loadData();

        todoItemAdapter = new TodoItemAdapter(items, onLongClickListener);
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
    }

    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void initUI() {
        listOfTodos = findViewById(R.id.listOfTodos);
        addButton = findViewById(R.id.add_todo_item_btn);
        addNewTodoEditText = findViewById(R.id.add_todo_item_edit_text);
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