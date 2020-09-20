package com.vogella.android.rxjava.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // data holder
    List<String> items;

    // views
    Button addButton;
    EditText addNewTodoEditText;

    // adapters and recyclerviews
    RecyclerView listOfTodos;
    TodoItemAdapter todoItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        items = new ArrayList<>();
        items.add("Finish Codepath Prework");
        items.add("Call Teacher");

        todoItemAdapter = new TodoItemAdapter(items);
        listOfTodos.setAdapter(todoItemAdapter);
        listOfTodos.setLayoutManager(new LinearLayoutManager(this));

        setUpOnclickListeners();
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
            }
        });
    }

    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void initUI() {
        listOfTodos = findViewById(R.id.listOfTodos);
        addButton = findViewById(R.id.add_todo_item_btn);
        addNewTodoEditText = findViewById(R.id.add_todo_item_edit_text);
    }
}