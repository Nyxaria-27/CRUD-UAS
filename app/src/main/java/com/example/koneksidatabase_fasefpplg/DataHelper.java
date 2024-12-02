package com.example.koneksidatabase_fasefpplg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel biodata dengan kolom no, nama, tgl, jk, dan alamat
        String sql = "CREATE TABLE biodata (no INTEGER PRIMARY KEY, " +
                "nama TEXT NULL, tgl TEXT NULL, jk TEXT NULL, alamat TEXT NULL);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        // Insert data default ke tabel biodata
        sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) " +
                "VALUES ('1001', 'Fathur', '1994-02-03', 'Laki-laki', 'Jakarta');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Digunakan untuk mengupdate database jika ada perubahan pada struktur
        Log.d("Data", "onUpgrade: Database version changed from " + oldVersion + " to " + newVersion);
        // Contoh: Drop table jika ada perubahan versi
        db.execSQL("DROP TABLE IF EXISTS biodata");
        onCreate(db);
    }
}
