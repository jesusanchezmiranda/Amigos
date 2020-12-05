package com.jesus.amigos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.jesus.amigos.R;
import com.jesus.amigos.model.room.pojo.Llamada;
import com.jesus.amigos.viewmodel.ViewModelActivity;

import java.util.List;

public class MostrarLlamadas extends AppCompatActivity {

    private TextView tvLlamadas;
    private ViewModelActivity viewModelActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_llamadas);
        init();
    }

    private void init() {
        tvLlamadas = findViewById(R.id.tvListaLlamadas);

        viewModelActivity = new ViewModelProvider(this).get(ViewModelActivity.class);

        viewModelActivity.getLiveListaLlamadas().observe(this, new Observer<List<Llamada>>() {
            @Override
            public void onChanged(List<Llamada> llamadas) {
                for (int i = 0; i < llamadas.size(); i++) {
                    tvLlamadas.append(llamadas.get(i).toString()+ "\n");
                }
            }
        });


    }
}