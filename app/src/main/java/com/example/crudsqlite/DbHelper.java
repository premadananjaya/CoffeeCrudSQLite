package com.example.crudsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    Context mContext;

    public static final String database_name = "db_coffee";
    public static final int VERSION = 1;

    public DbHelper(Context context){
        super(context, database_name, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String sql = "CREATE TABLE tb_coffee (harga integer primary key,"+" " +
                    "nama text, jenis text);";
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("error", "failed");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    //menambah data

    public boolean insertData(int harga, String nama, String jenis){
        try{
            String sql = "INSERT INTO tb_coffee VALUES ("+harga+",'"+nama+"'," +
                    "'"+jenis+"');";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            Toast.makeText(mContext,"Berhasil add data",
                    Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Cursor showData(){
        try{
            String sql = "SELECT * FROM tb_coffee";
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    //edit data

    public boolean editData(String hargaOld, String hargaNew, String nama,
                            String jenis){
        try{
            String sql = "UPDATE tb_coffee SET " + "harga=" +hargaNew+ ", " +
                    "nama= ' "+nama+"', "+ "jenis='"+jenis+"', " + "WHERE harga = "+hargaOld+";";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            Toast.makeText(mContext,"Berhasil edit data", Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteData(String hargaOld){
        try{
            String sql = "DELETE FROM tb_coffee WHERE harga = "+hargaOld+";";
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql);
            Toast.makeText(mContext,"Berhasil hapus data",
                    Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
