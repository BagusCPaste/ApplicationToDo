package com.bagus.applicationtodo.ToDo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bagus.applicationtodo.MainActivity;
import com.bagus.applicationtodo.R;
import com.bagus.applicationtodo.SQLiteDatabase.DatabaseHelper;
import com.bagus.applicationtodo.fragment.ToDoFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditToDo extends AppCompatActivity {
    EditText edtnama, edtstatus;
    ImageView edtgambar;
    Button btnUpdate, btnDelete;

//    DatePickerDialog.OnDateSetListener date;
//    Calendar myCalender;

    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        edtnama = findViewById(R.id.edtnama);
        edtstatus = findViewById(R.id.edtstatus);
        edtgambar = findViewById(R.id.edtgamabr);

        btnUpdate = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        myDb = new DatabaseHelper(this);
//        myCalender = Calendar.getInstance();

        edtnama.setText(getIntent().getStringExtra("namaToDo"));
        edtstatus.setText(getIntent().getStringExtra("statusToDo"));


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
                String nama = edtnama.getText().toString();
                String status = edtstatus.getText().toString();
                String id = getIntent().getStringExtra("idtoDo");

                if (nama.equals("") || status.equals("")){
                    if (nama.equals("")){
                        edtnama.setError("Nama harus diisi");
                    }if (nama.equals("")){
                        edtstatus.setError("Status harus diisi");
                    }

                }else{
                    boolean isUpdate = myDb.updateData(nama, status, id);
                    if (isUpdate){
                        Toast.makeText(EditToDo.this,"Data berhasil diubah", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(EditToDo.this,"Data gagal diubah", Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(EditToDo.this, MainActivity.class));
                    finish();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("idToDo");
                Integer deleterows = myDb.deleteData(id);

                if (deleterows > 0){
                    Toast.makeText(EditToDo.this,"Data berhasil dhapus", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditToDo.this,"Data gagal dhapus", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(EditToDo.this, MainActivity.class));
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