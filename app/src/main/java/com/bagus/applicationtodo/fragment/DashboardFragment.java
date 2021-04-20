package com.bagus.applicationtodo.fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toolbar;

import com.bagus.applicationtodo.R;
import com.bagus.applicationtodo.SQLiteDatabase.DatabaseHelper;
import com.bagus.applicationtodo.ToDo.ToDo;
import com.bagus.applicationtodo.ToDo.ToDoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    private ToDoAdapter adapter;
    List<ToDo> list = new ArrayList<ToDo>();
    ToDoAdapter toDoAdapter;
    DatabaseHelper myDB;
    RecyclerView rvToDo;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        View v =inflater.inflate(R.layout.fragment_dashboard, container, false);
//        getData();
        myDB = new DatabaseHelper(getActivity());
        list.addAll(myDB.getAllData());
        rvToDo = v.findViewById(R.id.rv_ToDo);
        rvToDo.setLayoutManager(new LinearLayoutManager(getActivity()));
        toDoAdapter = new ToDoAdapter(getActivity(), list);
        toDoAdapter.notifyDataSetChanged();
        rvToDo.setAdapter(toDoAdapter);
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        return v;
    }

    //Berisi Statement-Statement Untuk Mengambi Data dari Database
    @SuppressLint("Recycle")
    protected void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = myDB.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_NAME,null);

        //Memulai Cursor pada Posisi Awal
        cursor.moveToFirst();

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            //Memasukan semua data dari variable NIM, Nama dan Jurusan ke parameter Class DataFiter
            list.add(new ToDo(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2)));
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = new SearchView (getActivity());
        searchItem.getActionView();
        searchView.setQueryHint("Cari apa Bang..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Data akan berubah saat user menginputkan text/kata kunci pada SearchView
                newText = newText.toLowerCase();
                ArrayList<ToDo> ToDo = new ArrayList<>();
                for(ToDo data : list){
                    String nama = data.getNamaToDo().toLowerCase();
                    if(nama.contains(newText)){
                        ToDo.add(data);
                    }
                }
                adapter.setFilter(ToDo);
                return true;
            }
        });
        searchItem.getActionView();
    }
    //Memasukan semua data dari variable Nama dan Gambar ke parameter Class DataFiter(Nama,ImageID)
//    private void DaftarItem(){
//        int count = 0;
//        for (String nama : ){
//            arrayList.add(new ToDoAdapter(nama,);
//            count++;
//        }
//    }
}