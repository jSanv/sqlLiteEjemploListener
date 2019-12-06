package com.example.sqlliteejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;

//imports de base de datos SQLLITE
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import entidades.productos;

public class MainActivity extends AppCompatActivity {

    private EditText etcodigo, etnombre, etprecio;
    TextView cantidad;
    //estatico para que no cambie en la horientacion de pantalla
    static  int cant;
    List<productos> datasource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //controles
        etcodigo = (EditText) findViewById(R.id.txtcodigo);
        etnombre = (EditText) findViewById(R.id.txtnombre);
        etprecio = (EditText) findViewById(R.id.txtprecio);
        cantidad = findViewById(R.id.lblcantidad);

        //leer lista
        datasource = leer();

        actualizardatos();


    }

    //este es solo para el contador label que se muestra en la principal, borrar si no esta en uso
    private void actualizardatos() {
        //asignamos la cantidad de resultados de la lista de productos
        datasource = leer();
        cant = datasource.size();
        cantidad.setText("Productos en la base de datos: "+ Integer.toString(cant));
    }

    public void Registrar(View view) {
        //asignamos la conexion
        ConexionsqLiteHelper admin = new ConexionsqLiteHelper(this, "bdejemplo", null, 1);
        //abrimos la base de datos
        SQLiteDatabase bd = admin.getWritableDatabase();

        //controles
        String codigo = etcodigo.getText().toString();
        String nombre = etnombre.getText().toString();
        String precio = etprecio.getText().toString();


        //validamos cajas de texto
        if (!codigo.isEmpty() && !nombre.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("precio", precio);

            //insertamos en la base de datos
            bd.insert("articulos", null, registro);
            //cerramos la base de datos
            bd.close();
            //limpiamos las cajas de texto
            etcodigo.setText("");
            etnombre.setText("");
            etprecio.setText("");

            //confirmamos en mensaje de texto
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();

        } else {
            //si los campos estan vacios, avisamos al usuario por medio de este mensaje
            Toast.makeText(this,"No dejar campos vacios", Toast.LENGTH_SHORT).show();
        }
        //actualizamos los datos del label, borrar si no se usa el contador de muestra
        actualizardatos();
    }

    //METODO PARA CONSULTAR

    public void Buscar(View view){
        //abrimos la base de datos
        ConexionsqLiteHelper admin = new ConexionsqLiteHelper(this, "bdejemplo", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        // variable String de la caja de texto
        String codigo = etcodigo.getText().toString();

        //validamos que no este vacio
        if(!codigo.isEmpty()){
            //consultamos por medio de CONSULTA SQL
            Cursor fila  = bd.rawQuery
                    ("select nombre, precio from articulos where codigo="+ codigo, null);


            if(fila.moveToFirst()){
                //el cuadro de la consulta que hemos hecho, solo presenta 2 columnas, asi que la posicion 0 es el nomnbre
                //y la posicion 1 es el precio
                etnombre.setText(fila.getString(0));
                etprecio.setText(fila.getString(1));
                //cerramos la base de datos
                bd.close();
            } else {
                Toast.makeText(this,"Codigo no existe", Toast.LENGTH_SHORT).show();
                bd.close();
            }

        } else{
            Toast.makeText(this,"Debes introducir el codigo", Toast.LENGTH_SHORT).show();
        }
    }

    //METODO PARA ELIMINAR

    public void Eliminar(View view){
        //abrimos la base de datos
        ConexionsqLiteHelper admin = new ConexionsqLiteHelper(this, "bdejemplo", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //validamos la caja de texto de la interfaz
        String codigo = etcodigo.getText().toString();

        if(!codigo.isEmpty()){
            //comando de SQLLITE para eliminar donde el CODIGO = codigo(de la caja de texto)
             int cantidad =  bd.delete("articulos", "codigo= " + codigo, null);
             //cerramos la base de datos
             bd.close();
             //vaciamos los campos
             etcodigo.setText("");
             etnombre.setText("");
             etprecio.setText("");

             // si la eliminacion es 1 es por que es TRUE y se ha eliminado
             if(cantidad == 1){
                 Toast.makeText(this, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
             } else {
                 // no existe el codigo en la base de datos
                 Toast.makeText(this, "El codigo no existe", Toast.LENGTH_SHORT).show();
             }

             // no esta ingresando codigo en la caja de texto
        }else{
            Toast.makeText(this, "Introduce el codigo", Toast.LENGTH_SHORT).show();
        }
        //actualizamos los datos del label, borrar si no se usa el contador de muestra
        actualizardatos();
    }

    //METODO PARA ACTUALIZAR

    public void Modificar(View view) {
        //abrimos la base de datos
        ConexionsqLiteHelper admin = new ConexionsqLiteHelper(this, "bdejemplo", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //validamos las cajas de texto
        String codigo = etcodigo.getText().toString();
        String nombre = etnombre.getText().toString();
        String precio = etprecio.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !precio.isEmpty()){
            //evaluamos los registros de la base de datos
            ContentValues registro = new ContentValues();

            //ingresamos los actuales
            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("precio", precio);

           // int cantidad = bd.update("articulos", registro, "codigo= 1"+ codigo, null);
            //comando de actualizar por defecto de SQL
            int cantidad = bd.update("articulos", registro, "codigo="+ codigo, null);
            //cerramos la base de datos
            bd.close();

            //si el comando da 1 es TRUE y elimino
            if(cantidad==1){
                Toast.makeText(this,"Modificacion exitosa", Toast.LENGTH_SHORT).show();
            }else {
                // no existe en la base de datos por quedarse en 0 que es FALSE
                Toast.makeText(this, "Articulo no existe", Toast.LENGTH_SHORT).show();
            }

        }else {
            //no hay nada en la caja de texto
            Toast.makeText(this, "Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }

        //actualizamos los datos del label, borrar si no se usa el contador de muestra
        actualizardatos();

    }

    //evento para ir al activity mostrar
    public void clickmostrar(View view)
    {
        Intent as = new Intent(this, mostrarproductos.class);
        startActivity(as);
    }


    public List<productos> leer()
    {
        datasource = new ArrayList<>();
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

    //actualizacion de datos para la orientacion de pantalla


    @Override
    protected void onResume() {
        super.onResume();
       actualizardatos();
    }
}
