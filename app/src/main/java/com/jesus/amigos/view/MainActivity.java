package com.jesus.amigos.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jesus.amigos.R;
import com.jesus.amigos.model.room.pojo.Contacto;
import com.jesus.amigos.model.room.pojo.LlamadasAmigo;
import com.jesus.amigos.view.adapter.RecyclerAmigosAdapter;
import com.jesus.amigos.view.adapter.RecyclerContactosAdapter;
import com.jesus.amigos.viewmodel.ViewModelActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISO = 100;

    private FloatingActionButton fabAnadir, fabLlamadas;

    private Intent intent;

    private List<Contacto> listaAmigos = new ArrayList<>();
    private List<LlamadasAmigo> listaLlamadasAmigo = new ArrayList<>();

    private ViewModelActivity viewModelActivity;

    private String[] codigosPermisos = {Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        fabAnadir = findViewById(R.id.fabAñadir);
        fabLlamadas = findViewById(R.id.fabLlamadas);

        getPermissions();

        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerAmigos);
        final RecyclerAmigosAdapter adapter = new RecyclerAmigosAdapter(MainActivity.this, listaLlamadasAmigo);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        viewModelActivity.getLiveListaContactos().observe(this, new Observer<List<Contacto>>() {
//            @Override
//            public void onChanged(List<Contacto> contactos) {
//                listaAmigos.clear();
//                listaAmigos.addAll(contactos);
//                adapter.notifyDataSetChanged();
//            }
//        });

        viewModelActivity.getLiveLlamadasAmigoList().observe(this, new Observer<List<LlamadasAmigo>>() { //hacemos un observer de la consulta para que se vaya actualizando automaticamente
            @Override
            public void onChanged(List<LlamadasAmigo> llamadasAmigos) {
                listaLlamadasAmigo.clear();
                listaLlamadasAmigo.addAll(llamadasAmigos);
                adapter.notifyDataSetChanged();
            }
        });



        fabAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ListaContactosTelefono.class);
                startActivity(intent);
            }
        });

        fabLlamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MostrarLlamadas.class);
                startActivity(intent);
            }
        });






    }


    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int phonePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
            int callPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
            int contactsPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

            if (phonePermission == PackageManager.PERMISSION_GRANTED && callPermission == PackageManager.PERMISSION_GRANTED && contactsPermission == PackageManager.PERMISSION_GRANTED) {

            }else{
                //you have to request the permissions that you haven´t got

                if (shouldShowRequestPermissionRationale(String.valueOf(codigosPermisos))){
                    explainPermission(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG);
                }else{
                    requestPermissions(codigosPermisos, PERMISO);
                }

            }

        } else {
            //do the next because you have all the permissions
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISO:
                if(grantResults.length>0){
                    boolean ReadCallLogPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadPhoneStatePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadContacts = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    if(ReadCallLogPermission && ReadPhoneStatePermission && ReadContacts ){

                    }else{
                        explainPermission();
                    }
                }
        }
    }

    @SuppressLint("NewApi")
    private void pidoPermiso(){
        requestPermissions(codigosPermisos, PERMISO);
    }


    private void explainPermission(String... permissions) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permisos Necesarios");
        builder.setMessage("Necesitas los permisos para ejecutar la aplicacion correctamente");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pidoPermiso();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.fromParts("package", getPackageName(), null)));
            }
        });
        builder.show();

    }


}