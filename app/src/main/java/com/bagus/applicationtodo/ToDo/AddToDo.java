package com.bagus.applicationtodo.ToDo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bagus.applicationtodo.MainActivity;
import com.bagus.applicationtodo.R;
import com.bagus.applicationtodo.SQLiteDatabase.DatabaseHelper;
import com.bagus.applicationtodo.fragment.ToDoFragment;

public class AddToDo extends AppCompatActivity {
    EditText namaToDo, statusToDo;
    ImageView gambar;
    Button btnUpdate, btnDelete;

//    DatePickerDialog.OnDateSetListener date;
//    Calendar myCalender;

    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        namaToDo = findViewById(R.id.nama);
        statusToDo = findViewById(R.id.status);
        gambar = findViewById(R.id.gambar);

        btnUpdate = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnCancel);

        myDb = new DatabaseHelper(this);
//        myCalender = Calendar.getInstance();

        namaToDo.setText(getIntent().getStringExtra("namaToDo"));
        statusToDo.setText(getIntent().getStringExtra("statusToDo"));


//        date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                myCalender.set(Calendar.YEAR, year);
//                myCalender.set(Calendar.MONTH, month);
//                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel(); // updateLabel
//            }
//        };
//        edtDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(EditToDo.this, date,
//                        myCalender.get(Calendar.YEAR),
//                        myCalender.get(Calendar.MONTH),
//                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = namaToDo.getText().toString();
                String status = statusToDo.getText().toString();

                String id = getIntent().getStringExtra("idtoDo");

                if (nama.equals("") || status.equals("")){
                    if (nama.equals("")){
                        namaToDo.setError("Nama harus diisi");
                    }if (nama.equals("")){
                        statusToDo.setError("Status harus diisi");
                    }

                }else{
                    boolean isInserted = myDb.insertData(nama, status);
                    if (isInserted){
                        Toast.makeText(AddToDo.this,"Data berhasil ditambahkan ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddToDo.this,"Data gagal ditambahkan ", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(AddToDo.this, MainActivity.class));
                    finish();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("idtodo");
                Integer deleterows = myDb.deleteData(id);

                if (deleterows > 0){
                    Toast.makeText(AddToDo.this,"Data berhasil dhapus", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddToDo.this,"Data gagal dhapus", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(AddToDo.this, MainActivity.class));
                finish();
            }
        });
    }
    //untuk mengaudate text
//    private void updateLabel(){
//        String myformat = "dd-mm-yyyy";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myformat, Locale.US);
//        edtDate.setText(simpleDateFormat.format(myCalender.getTime()));
//    }
}