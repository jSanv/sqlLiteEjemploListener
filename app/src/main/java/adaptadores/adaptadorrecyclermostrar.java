package adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//importar entidad
import com.example.sqlliteejemplo.R;

import entidades.productos;

import java.util.List;

public class adaptadorrecyclermostrar extends RecyclerView.Adapter<viewholderproductos> implements View.OnClickListener {


    //esta parte es del constructor
    private List<productos> listContactos;

    //metodo de escucha click
    private View.OnClickListener listener;

    public adaptadorrecyclermostrar(List<productos> datasources){
        //Inicializamos el datasources
        this.listContactos = datasources;
    }


    //este metodo solo se usa para el metodo onclick listener
    @Override
    public void onClick(View view) {

        if(listener!=null)
        {
            listener.onClick(view);
        }

    }

    //metodo de escucha onclicklistener
    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }





    //estos son los procesos generados por la clase

    @NonNull
    @Override
    public viewholderproductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Creamos la vista usando el Layout de plantilla
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantillaproductos,parent,false);

        //metodo de escucha del listener por si lo necesitamos
        v.setOnClickListener(this);

        viewholderproductos vhContactos = new viewholderproductos(v);
        return vhContactos;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderproductos holder, int position) {


        holder.getLblnombre().setText("Nombre: "+ this.listContactos.get(position).getNombre());
        holder.getLblcodigo().setText("Precio: "+ Double.toString(this.listContactos.get(position).getPrecio()));
        holder.getLblprecio().setText("Codigo: "+ Integer.toString( this.listContactos.get(position).getCodigo()));


    }

    @Override
    public int getItemCount() {
        return this.listContactos.size();
    }
}
