package dam.isi.frsf.utn.edu.ar.laboratorio03;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class AltaActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    EditText oferta;
    Spinner categoria;
    RadioButton US$, Euro, AR$, Libra, R$;
    ArrayAdapter<String> adaptador;
    Trabajo NewTrabajo;
    int j, k;
    ArrayList<Trabajo> arrayTrabajo;
    Bundle contenedor=new Bundle();
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        oferta = (EditText) findViewById(R.id.Oferta);
        categoria = (Spinner)findViewById(R.id.Categoria);
        US$=(RadioButton)findViewById(R.id.US$);
        Euro=(RadioButton)findViewById(R.id.Euro);
        AR$=(RadioButton)findViewById(R.id.AR$);
        Libra=(RadioButton)findViewById(R.id.Libra);
        R$=(RadioButton)findViewById(R.id.R$);
        Bundle contenedor= new Bundle();

       adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
       k= Categoria.CATEGORIAS_MOCK.length;
       j=Trabajo.TRABAJOS_MOCK.length;





        for(int i=0; i<k; i++)
        adaptador.add(Categoria.CATEGORIAS_MOCK[i].getDescripcion());

        categoria.setAdapter(adaptador);




    }


    public void Guardar(View view) {



        NewTrabajo = new Trabajo();

        NewTrabajo.setId(j+1);
        NewTrabajo.setDescripcion(oferta.getText().toString());


        categoria.setOnItemClickListener(new AdapterView.OnItemClickListener()


        {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int m, long l) {

                for (int i=0; i<k; i++)
                    if (categoria.getItemAtPosition(m).equals(Categoria.CATEGORIAS_MOCK[i].getDescripcion()))
                        NewTrabajo.setCategoria(Categoria.CATEGORIAS_MOCK[i]);


            }
        });



        Intent i = new Intent(AltaActivity.this, MainActivity.class);
        i.putExtra("Descripcion", NewTrabajo.getDescripcion());
        i.putExtra("Categoria", NewTrabajo.getCategoria().getDescripcion());

        startActivity(i);




    }





    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Alta Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
