package com.bagus.applicationtodo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
    EditText namaToDo, statusToDo;
    ImageView gambar;
    Button btnUpdate, btnDelete;
    DatabaseHelper myDb;
    public ToDoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);

        namaToDo = view.findViewById(R.id.nama);
        statusToDo = view.findViewById(R.id.status);
        gambar = view.findViewById(R.id.gambar);

        btnUpdate = view.findViewById(R.id.btnAddd);
        btnDelete = view.findViewById(R.id.btnCancel);

        myDb = new DatabaseHelper(getActivity());

        namaToDo.setText(getActivity().getIntent().getStringExtra("namaToDo"));
        statusToDo.setText(getActivity().getIntent().getStringExtra("statusToDo"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = namaToDo.getText().toString();
                String status = statusToDo.getText().toString();

                String id = getActivity().getIntent().getStringExtra("idtoDo");

                if (nama.equals("") || status.equals("")){
                    if (nama.equals("")){
                        namaToDo.setError("Nama harus diisi");
                    }if (nama.equals("")){
                        statusToDo.setError("Status harus diisi");
                    }

                }else{
                    boolean isInserted = myDb.insertData(nama, status);
                    if (isInserted){
                        Toast.makeText(getActivity(),"Data berhasil ditambahkan ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(),"Data gagal ditambahkan ", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getActivity().getIntent().getStringExtra("idtodo");
                Integer deleterows = myDb.deleteData(id);

                if (deleterows > 0){
                    Toast.makeText(getActivity(),"Data berhasil dhapus", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"Data gagal dhapus", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

//        FloatingActionButton btn = (FloatingActionButton)view.findViewById(R.id.fab);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), AddToDo.class);
//                startActivity(i);
//            }
//        });
        return view;
    }

}