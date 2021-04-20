package com.bagus.applicationtodo.ToDo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bagus.applicationtodo.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {
    Context context;
    List<ToDo> todo;

    public ToDoAdapter(Context context, List<ToDo> todo) {
        this.context = context;
        this.todo = todo;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new  MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.namaToDo.setText(todo.get(position).getNamaToDo());
        holder.statusToDo.setText(todo.get(position).getStatusToDo());
//        holder.datetodo.setText(todo.get(position).getDatetodo());

        final String getnamaToDo = todo.get(position).getNamaToDo();
        final String getstatusToDo = todo.get(position).getStatusToDo();
//        final String getDateTodo = todo.get(position).getDatetodo();
        final String getIdToDo = todo.get(position).getIdToDo();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditToDo.class);
                i.putExtra("namaToDo", getnamaToDo);
                i.putExtra("statusToDo", getstatusToDo);
//                i.putExtra("datetodo", getDateTodo);
                i.putExtra("idoDo", getIdToDo);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return todo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView namaToDo, statusToDo;
        ImageView imageToDo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namaToDo = (TextView)itemView.findViewById(R.id.namaTodo);
            statusToDo = (TextView)itemView.findViewById(R.id.statusTodo);
             imageToDo = (ImageView)itemView.findViewById(R.id.gambarTodo);
        }
    }
}
