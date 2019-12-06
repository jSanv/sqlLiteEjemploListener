package com.example.sqlliteejemplo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionsqLiteHelper extends SQLiteOpenHelper {

    /*
    Esta clase por lo general es global, solo implementandola en cualquier activity y asi podemos gestionar
    los datos que hemos hecho
    * */

    // valores a recibir de la base de datos
    public ConexionsqLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bdsqLite) {
        //CREACION DE LA TABLA QUE USAREMOS
    bdsqLite.execSQL("create table articulos(codigo int primary key, nombre text, precio Double)");
    }

    //por defecto se deja vacio
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
