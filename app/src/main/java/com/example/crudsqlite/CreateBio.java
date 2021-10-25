package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateBio extends AppCompatActivity {
    EditText newHarga, newName, newJenis;
    Button btnCreate, btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bio);

        newHarga = findViewById(R.id.editTextHarga);
        newName = findViewById(R.id.editTextName);
        newJenis = findViewById(R.id.editTextJenis);
        btnCreate = findViewById(R.id.btnCreate);
        btnKembali = findViewById(R.id.btnCreateKembali);
    }

    public void createData(View view){
        String ckHarga = newHarga.getText().toString();
        String ckName = newName.getText().toString();
        String ckJenis = newJenis.getText().toString();


        boolean check = validasi(ckHarga, ckName, ckJenis);
        if (!check){
            Toast.makeText(getApplicationContext(),"isi semua data", Toast.LENGTH_SHORT).show();
        } else {
            boolean result = MainActivity.dbHelper.insertData(
                    Integer.parseInt(newHarga.getText().toString().trim()),
                    ckName.trim(),
                    ckJenis.trim());
            if (result){
                Intent intent = new Intent(CreateBio.this, MainActivity.class);
                startActivity(intent);
                CreateBio.this.finish();
            }
        }
    }

    public void kembali(View view){
        Intent intent = new Intent(CreateBio.this, MainActivity.class);
        startActivity(intent);
        CreateBio.this.finish();
    }

    public boolean validasi(String ckHarga, String ckName, String ckJenis) {
        if (ckHarga.length() == 0) {
            newHarga.requestFocus();
            newHarga.setError("Harga tidak boleh kosong!");
            return false;
        } else if (ckName.length() == 0) {
            newName.requestFocus();
            newName.setError("Nama tidak boleh kosong!");
            return false;
        } else if (ckJenis.length() == 0) {
            newJenis.requestFocus();
            newJenis.setError("Jenis tidak boleh kosong!");
            return false;
        }else{
            return true;
        }
    }
}