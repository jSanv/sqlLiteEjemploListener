package adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlliteejemplo.R;

public class viewholderproductos extends RecyclerView.ViewHolder {

    private TextView lblnombre;
    private TextView lblcodigo;
    private TextView lblprecio;

    public viewholderproductos(@NonNull View itemView) {
        super(itemView);

        this.lblnombre=((itemView.findViewById(R.id.lblnombre)));
        this.lblcodigo=((itemView.findViewById(R.id.lblcodigo)));
        this.lblprecio=((itemView.findViewById(R.id.lblprecio)));
    }


    public TextView getLblnombre() {
        return lblnombre;
    }

    public TextView getLblcodigo() {
        return lblcodigo;
    }

    public TextView getLblprecio() {
        return lblprecio;
    }
}
