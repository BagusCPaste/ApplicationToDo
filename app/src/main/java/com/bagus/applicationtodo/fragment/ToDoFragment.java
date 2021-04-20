package com.bagus.applicationtodo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bagus.applicationtodo.MainActivity;
import com.bagus.applicationtodo.R;
import com.bagus.applicationtodo.SQLiteDatabase.DatabaseHelper;
import com.bagus.applicationtodo.ToDo.AddToDo;
import com.bagus.applicationtodo.ToDo.ToDo;
import com.bagus.applicationtodo.ToDo.ToDoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ToDoFragment extends Fragment {

    List<ToDo> list = new ArrayList<ToDo>();
    ToDoAdapter toDoAdapter;
    DatabaseHelper myDB;
    RecyclerView rvToDo;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToDoFragment() {
        // Required empty public constructor
    }

    public static ToDoFragment newInstance(String param1, String param2) {
        ToDoFragment fragment = new ToDoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);

        myDB = new DatabaseHelper(getActivity());
        list.addAll(myDB.getAllData());
        rvToDo = view.findViewById(R.id.rv_ToDo);
        rvToDo.setLayoutManager(new LinearLayoutManager(getActivity()));
        toDoAdapter = new ToDoAdapter(getActivity(), list);
        toDoAdapter.notifyDataSetChanged();
        rvToDo.setAdapter(toDoAdapter);

        FloatingActionButton btn = (FloatingActionButton)view.findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddToDo.class);
                startActivity(i);
            }
        });
        return view;
    }

}