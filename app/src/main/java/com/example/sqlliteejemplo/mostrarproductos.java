package com.example.sqlliteejemplo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import adaptadores.*;
import entidades.productos;

import java.util.ArrayList;
import java.util.List;


public class mostrarproductos extends AppCompatActivity {

    //variables de control, por defecto seguir la misma estetica

    //estatica para que no se pierdan los valores al horientar la pantalla
    private static List<productos> datasource;
    RecyclerView listaproductos;
    // llamada de cargar datos
    private TextView totalsalida;
    private Button borrarregistros;

    LinearLayoutManager manager;
    adaptadorrecyclermostrar adaptadorobjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarproductos);


//////////////////////////// CARGAMOS LOS DATOS Y ADAPTAMOS

        listaproductos = findViewById(R.id.idlistadeproductos);
        datasource = new ArrayList<>();

        //leemos los datos de la base de datos con esta funcion
        datasource = leer();

        this.manager = new LinearLayoutManager(this);


        this.adaptadorobjeto = new adaptadorrecyclermostrar(this.datasource);


        //Establecemos las propiedades
        this.listaproductos.setHasFixedSize(true);
        this.listaproductos.setLayoutManager(this.manager);

        // adaptamos los datos
        this.listaproductos.setAdapter(this.adaptadorobjeto);

////////////////////////////FIN DE ADAPTACION ////////////////////////////////////////////////////////////



        //////////////////METODO PARA EL LISTENER //////////////////////////////////////////////////////////
        // este es el listener para seleccionar el objeto que esta en lista
        adaptadorobjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Toast.makeText(gestiondeproductos.this, "Seleccionado "+datasource.get(lista.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(mostrarproductos.this).setTitle("¡Aviso!").setMessage("¿Desea editar el registro \"" + datasource.get(listaproductos.getChildAdapterPosition(view)).getNombre() + "\"?")
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Actividad que hara con el seleccionado si el usario da en OK o SI
                                Toast.makeText(mostrarproductos.this, "Usted ha seleccionado "+ datasource.get(listaproductos.getChildAdapterPosition(view)).getNombre() , Toast.LENGTH_SHORT).show();

                            }
                        }).show();
            }
        });
/////////////////////////////////////FIN DEL METODO ESCUCHA //////////////////////////////////////////////

    }

    // asignando los valores que tengo registrado en SQL para un LIST
    public List<productos> leer()
    {
        //consulta sql
        String sql = "SELECT * FROM articulos";
        //abrir base de datos en bdejemplo
        ConexionsqLiteHelper admin = new ConexionsqLiteHelper(this, "bdejemplo", null, 1);
        //abrir conexion
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor = bd.rawQuery(sql,null);

        //aqui llenaremos un Arraylist con los datos que vengan de la tabla de la consulta sql
        if(cursor.moveToFirst())
        {
            do {


            int codigo = cursor.getInt(cursor.getColumnIndex("codigo"));
            Double precio =  cursor.getDouble(cursor.getColumnIndex("precio"));
            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));

            datasource.add(new productos(codigo,nombre,precio));

            } while (cursor.moveToNext());
        }

        //cerramos los objetos de control
        cursor.close();
        bd.close();

        // regresamos nuestra lista llena
        return datasource;

    }



}
