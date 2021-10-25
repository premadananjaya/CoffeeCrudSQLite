package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditBio extends AppCompatActivity {
    protected DbHelper dbHelper;
    Cursor cursor;

    EditText editHarga, editName, editJenis;
    Button btnEdit, btnQuit;
    String oldHarga, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bio);

        editHarga = findViewById(R.id.editNewHarga);
        editName = findViewById(R.id.editNewName);
        editJenis = findViewById(R.id.editNewJenis);
        btnEdit = findViewById(R.id.btnEdit);
        btnQuit = findViewById(R.id.btnEditKembali);

        DbHelper dbHelper = new DbHelper(EditBio.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_coffee WHERE harga = '" +
                getIntent().getStringExtra("MainHarga") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            editHarga.setText(cursor.getString(0).toString());
            editName.setText(cursor.getString(1).toString());
            editJenis.setText(cursor.getString(2).toString());
            oldHarga = cursor.getString(0);
        }
    }

    public void editData(View view){
        try{
            boolean result = MainActivity.dbHelper.editData(
                    oldHarga,
                    editHarga.getText().toString().trim(),
                    editName.getText().toString().trim(),
                    editJenis.getText().toString().trim()
            );

        } catch (Exception err){
            err.printStackTrace();
            Toast.makeText(getApplicationContext(),"isi semua data",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void editQuit(View view){
        Intent intent = new Intent(EditBio.this, MainActivity.class);
        startActivity(intent);
        EditBio.this.finish();
    }
}