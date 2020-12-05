package com.jesus.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jesus.amigos.R;
import com.jesus.amigos.model.room.pojo.Contacto;
import com.jesus.amigos.model.room.pojo.LlamadasAmigo;
import com.jesus.amigos.view.adapter.RecyclerAmigosAdapter;
import com.jesus.amigos.view.adapter.RecyclerContactosAdapter;
import com.jesus.amigos.viewmodel.ViewModelActivity;

import java.util.ArrayList;
import java.util.List;

public class InfoAmigos extends AppCompatActivity {

    private TextView tvTelefono, tvNumLlamadas;
    private EditText etNombre, etFecha;
    private ViewModelActivity viewModelActivity;
    private Intent intent;
    private Button btActualizar, btBorrar;
    private Contacto amigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_amigos);
        init();
    }

    private void init() {

        tvNumLlamadas = findViewById(R.id.tvNumLlamadas);
        tvTelefono = findViewById(R.id.tvTel);
        etFecha = findViewById(R.id.etFecha);
        etNombre = findViewById(R.id.etNombre);
        btActualizar = findViewById(R.id.btActualizar);
        btBorrar = findViewById(R.id.btBorrar);

        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class);
        intent = getIntent();

        ArrayList<String> contacto = intent.getStringArrayListExtra(RecyclerAmigosAdapter.B);

        String nom = contacto.get(0);
        String tlf = contacto.get(1);
        String fecha = contacto.get(2);
        long id = Long.parseLong(contacto.get(3));
        String numLlamadas = contacto.get(4); //obtener el numero de llamadas


        etNombre.setText(nom);
        tvTelefono.setText(tlf);
        etFecha.setText(fecha);
        tvNumLlamadas.setText(numLlamadas); // mostrar el numero de llamadas *****

        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String fecha = etFecha.getText().toString();
                amigo = new Contacto();
                amigo.setId(id);
                amigo.setNombre(nombre);
                amigo.setFecNac(fecha);
                amigo.setTel(tlf);
                viewModelActivity.update(amigo);
                intent = new Intent(InfoAmigos.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelActivity.delete(id);
                intent = new Intent(InfoAmigos.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}