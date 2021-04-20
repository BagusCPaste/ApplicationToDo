package com.bagus.applicationtodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.bagus.applicationtodo.fragment.DashboardFragment;
import com.bagus.applicationtodo.fragment.ToDoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentPage(new ToDoFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.Bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

//                Daftar Halaman fragment
                switch (item.getItemId()){
                    case R.id.dasboard_navigation:
                        fragment = new DashboardFragment();
                        break;

                    case R.id.ToDo_navigation :
                        fragment = new ToDoFragment();
                        break;
                }
                return getFragmentPage(fragment);
            }
        });
    }
    //Menampilkan halaman Fragment
    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}