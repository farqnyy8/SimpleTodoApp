package com.vogella.android.rxjava.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// responsible for getting data and putting in RecyclerView
public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemAdapter.TodoViewHolder>{

    private List<String> todoItemsStrings;

    public TodoItemAdapter(List<String> todoItemsStrings) {
        this.todoItemsStrings = todoItemsStrings;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // wrap it inside a view holder and return
        return new TodoViewHolder(todoView);
    }

    // responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        // grab the item at the position
        String item = todoItemsStrings.get(position);

        // bind the item into the specified view holder
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return todoItemsStrings.size();
    }

    // Container to provide access to the views that represent each row of data
    class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView item;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(android.R.id.text1);
        }

        // update view inside of the view holder with this data
        public void bind(String todoText) {
            item.setText(todoText);
        }
    }
}
