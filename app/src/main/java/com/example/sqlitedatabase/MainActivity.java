package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //verificar a existência de outros bancos de dados com esse nome
        database = openOrCreateDatabase("bd",MODE_PRIVATE, null);

        // executar SQL-DDl
        database.execSQL("CREATE TABLE IF NOT EXISTS pessoas(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR)");

        // inserir dados
        ContentValues pessoaValores = new ContentValues();
        pessoaValores.put("_id", 1);
        pessoaValores.put("Nome", "Amanda");
        database.insert("pessoas", null, pessoaValores);

        // recuperar dados
        Cursor cursor = database.rawQuery("SELECT * FROM pessoas" , null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            String registro = cursor.getString(cursor.getColumnIndex("_id"));
                    registro = cursor.getString(cursor.getColumnIndex("Nome"));
            Log.d("Tabela_Pessoas", registro);
            cursor.moveToNext();
        }
    }
}
