package com.example.koneksidatabase_fasefpplg;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DataHelper(this);

        // Inisialisasi EditText dan Button
        text1 = findViewById(R.id.editTextup1);
        text2 = findViewById(R.id.editTextup2);
        text3 = findViewById(R.id.editTextup3);
        text4 = findViewById(R.id.editTextup4);
        text5 = findViewById(R.id.editTextup5);
        ton1 = findViewById(R.id.button1);
        ton2 = findViewById(R.id.button2);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0));
            text2.setText(cursor.getString(1));
            text3.setText(cursor.getString(2));
            text4.setText(cursor.getString(3));
            text5.setText(cursor.getString(4));
        }

        // Event onClick untuk tombol simpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE biodata SET nama = '" +
                        text2.getText().toString() + "', tgl = '" +
                        text3.getText().toString() + "', jk = '" +
                        text4.getText().toString() + "', alamat = '" +
                        text5.getText().toString() + "' WHERE no = '" +
                        text1.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

        // Event onClick untuk tombol batal
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu, ini menambahkan item ke action bar jika ada
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
