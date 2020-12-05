package com.jesus.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jesus.amigos.R;
import com.jesus.amigos.model.room.pojo.Contacto;
import com.jesus.amigos.view.adapter.RecyclerContactosAdapter;
import com.jesus.amigos.viewmodel.ViewModelActivity;

import java.util.ArrayList;

public class InfoContactos extends AppCompatActivity {

    private TextView tvNombre, tvTelefono, tvFecha;
    private ViewModelActivity viewModelActivity;
    private Intent intent;
    private Button btAdd;
    private Contacto amigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contactos);
        init();
    }

    private void init() {

        tvNombre = findViewById(R.id.tvNombre);
        tvFecha = findViewById(R.id.tvFecha);
        tvTelefono = findViewById(R.id.tvTelefono);
        btAdd = findViewById(R.id.btAdd);
        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class);
        intent = getIntent();

        ArrayList<String> contacto = intent.getStringArrayListExtra(RecyclerContactosAdapter.A);

        String nom = contacto.get(0);
        String tlf = contacto.get(1);
        String fecha = contacto.get(2);

        tvNombre.setText(nom);
        tvTelefono.setText(tlf);
        tvFecha.setText(fecha);
        amigo = new Contacto(nom, tlf, fecha);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelActivity.insert(amigo);
                intent = new Intent(InfoContactos.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}