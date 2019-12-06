package adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlliteejemplo.R;

import  entidades.productos;

import java.util.List;

public class adptad extends BaseAdapter {

    //Declaracion de variables
    private List<productos> datasource;
    private Context cntx;
    private int IdLayoutPlantilla;

    public List<productos> GetData(){
        return this.datasource;
    }

    public adptad(Context context, int IdPlantilla, List<productos> sources){
        //Inicializamos las variables
        this.cntx = context;
        this.IdLayoutPlantilla = IdPlantilla;
        this.datasource = sources;
    }

    @Override
    public int getCount() {
        return this.datasource.size();
    }

    @Override
    public productos getItem(int position) {
        return this.datasource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Validamos que no exista un formato
        if(convertView == null){
            //Creamos el Administrador de Layout
            LayoutInflater inflater = (LayoutInflater) this.cntx.getSystemService(this.cntx.LAYOUT_INFLATER_SERVICE);
            //Le damos vida al archivo xml ahora es un Layout
            convertView = inflater.inflate(this.IdLayoutPlantilla,null);

            //Buscamos los controles de nuestra plantilla
            TextView labelNombre = convertView.findViewById(R.id.lblnombre);
            TextView labelcodigo = convertView.findViewById(R.id.lblcodigo);
            TextView labelprecio = convertView.findViewById(R.id.lblprecio);


            //Obtenemos el dato a mostrar

            labelNombre.setText(this.datasource.get(position).getNombre());
            labelprecio.setText( Double.toString(this.datasource.get(position).getPrecio()) );
            labelcodigo.setText(Integer.toString(this.datasource.get(position).getCodigo()));
        }
        return convertView;
    }
}
