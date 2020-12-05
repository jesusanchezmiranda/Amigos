package com.jesus.amigos.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jesus.amigos.R;
import com.jesus.amigos.model.room.pojo.Contacto;
import com.jesus.amigos.model.room.pojo.LlamadasAmigo;
import com.jesus.amigos.view.InfoAmigos;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAmigosAdapter extends RecyclerView.Adapter<RecyclerAmigosAdapter.ViewHolder> {

    private Context contexto;
    private List<LlamadasAmigo> contactos;
    private Intent intent;
    public static final String B = "B";


    public RecyclerAmigosAdapter(Context contexto, List<LlamadasAmigo> contactos) {
        this.contexto = contexto;
        this.contactos = contactos;
    }

    @NonNull
    @Override
    public RecyclerAmigosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amigos, parent, false);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ArrayList<String> contacto = new ArrayList<>();
        //pasarle el numero de llamadas tambien
        contacto.add(contactos.get(position).getContacto().getNombre());
        contacto.add(contactos.get(position).getContacto().getTel());
        contacto.add(contactos.get(position).getContacto().getFecNac());
        contacto.add(String.valueOf(contactos.get(position).getContacto().getId()));
        contacto.add(String.valueOf(contactos.get(position).getCount()));

        holder.elnombre.setText(contactos.get(position).getContacto().getNombre());
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(contexto, InfoAmigos.class);
                intent.putExtra(B, contacto);
                contexto.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView elnombre;
        ConstraintLayout parentlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            elnombre = itemView.findViewById(R.id.tvNombreAmigo);
            parentlayout = itemView.findViewById(R.id.parentlayout2);
        }


    }
}
