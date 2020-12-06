package com.jesus.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.jesus.amigos.R;
import com.jesus.amigos.model.room.pojo.Contacto;
import com.jesus.amigos.view.adapter.RecyclerContactosAdapter;
import com.jesus.amigos.viewmodel.ViewModelActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ListaContactosTelefono extends AppCompatActivity {

    private Contacto contacto = new Contacto();
    private List<Contacto> listaContactos;
    private ViewModelActivity viewModelActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos_telefono);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init() {

        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class);

        listaContactos = viewModelActivity.getListaContactos();

        obtenerContactos(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.recyclerContactos);
        final RecyclerContactosAdapter adapter = new RecyclerContactosAdapter(ListaContactosTelefono.this, listaContactos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    private void obtenerContactos( Context context) {

        String[] data = new String[]{ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String order = ContactsContract.Data.DISPLAY_NAME + " ASC";
        String selectionNumber = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        Cursor cursor =  context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                data,
                selectionNumber,
                null,
                order);
        while(cursor.moveToNext()){
            String nom = cursor.getString(0);
            String tlf = cursor.getString(1);
            String fecha = generaFechaAleatoria();

            contacto = new Contacto(nom, tlf, fecha);
            viewModelActivity.setListaContactos(contacto);

        }
        cursor.close();
    }


    public String generaFechaAleatoria() {
        Calendar fecha;
        Random aleatorio = new Random();

        fecha = Calendar.getInstance();
        fecha.set (aleatorio.nextInt(60)+1950, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("La fecha vale " + sdf.format(fecha.getTime()));

        String fec = sdf.format(fecha.getTime());

        return fec;
    }

}




