package dam.isi.frsf.utn.edu.ar.laboratorio03;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Date;
import java.util.ArrayList;

import static dam.isi.frsf.utn.edu.ar.laboratorio03.R.mipmap.ic_ar;
import static dam.isi.frsf.utn.edu.ar.laboratorio03.R.mipmap.ic_br;
import static dam.isi.frsf.utn.edu.ar.laboratorio03.R.mipmap.ic_eu;
import static dam.isi.frsf.utn.edu.ar.laboratorio03.R.mipmap.ic_uk;
import static dam.isi.frsf.utn.edu.ar.laboratorio03.R.mipmap.ic_us;

/**
 * Created by Mauricio on 07/10/2016.
 */

public class AdapterTrabajo extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Trabajo> items;



    public AdapterTrabajo (Activity activity, ArrayList<Trabajo> Items ){

      this.activity = activity;
      this.items = Items;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {

        return items.get(position);
    }

    @Override
    public long getItemId(int position) {

        return items.get(position).getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;
        if (convertView==null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inf.inflate(R.layout.activity_oferta, null);
        }



        Trabajo trabajo = items.get(position);

        TextView Puesto = (TextView) v.findViewById(R.id.textViewPuesto);
        Puesto.setText(trabajo.getCategoria().getDescripcion());

        TextView Empresa= (TextView) v.findViewById(R.id.textViewEmpresa);
        Empresa.setText(trabajo.getDescripcion());

        TextView CantHoras= (TextView) v.findViewById(R.id.textViewCantHoras);
        CantHoras.setText(Double.toString(trabajo.getHorasPresupuestadas()));

        TextView PesosHora= (TextView) v.findViewById(R.id.textViewPesosHora);
        PesosHora.setText(Double.toString(Math.round(trabajo.getPrecioMaximoHora()*100)/100));




        ImageView Pais = (ImageView) v.findViewById(R.id.Pais);

        if(trabajo.getMonedaPago()==1)
            Pais.setImageResource(ic_us);
            else if(trabajo.getMonedaPago()==2)
                 Pais.setImageResource(ic_eu);
                 else if(trabajo.getMonedaPago()==3)
                         Pais.setImageResource(ic_ar);
                        else if(trabajo.getMonedaPago()==4)
                            Pais.setImageResource(ic_uk);
                              else if(trabajo.getMonedaPago()==4)
                                    Pais.setImageResource(ic_br);

        TextView FechaFin = (TextView) v.findViewById(R.id.FechaFin);
        FechaFin.setText(DateFormat.format("dd.MM.yyyy", trabajo.getFechaEntrega()).toString());

        CheckBox EnIngles = (CheckBox) v.findViewById(R.id.EnIngles);
        if(trabajo.getRequiereIngles().booleanValue())
            EnIngles.setChecked(true);
        else EnIngles.setChecked(false);



        return v;
    }
}
