package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowBio extends AppCompatActivity {
    protected Cursor cursor;
    DbHelper dbHelper;
    TextView txHarga, txName, txJenis;
    Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bio);

        txHarga = findViewById(R.id.shHarga);
        txName = findViewById(R.id.shName);
        txJenis = findViewById(R.id.shJenis);
        btnKembali = findViewById(R.id.btnShowKembali);

        DbHelper dbHelper = new DbHelper(ShowBio.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_coffee WHERE harga = '" +
                getIntent().getStringExtra("MainHarga") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            txHarga.setText(cursor.getString(0).toString());
            txName.setText(cursor.getString(1).toString());
            txJenis.setText(cursor.getString(2).toString());
        }
    }

    public void showQuit(View view){
        Intent intent = new Intent(ShowBio.this, MainActivity.class);
        startActivity(intent);
        ShowBio.this.finish();
    }
}