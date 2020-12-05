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
import com.jesus.amigos.view.InfoContactos;
import com.jesus.amigos.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerContactosAdapter extends RecyclerView.Adapter<RecyclerContactosAdapter.ViewHolder> {

    private Context contexto;
    private List<Contacto> contactos;
    private Intent intent;
    public static final String A = "a";

    public RecyclerContactosAdapter(Context contexto, List<Contacto> contactos) {
        this.contexto = contexto;
        this.contactos = contactos;
    }

    @NonNull
    @Override
    public RecyclerContactosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ArrayList<String> contacto = new ArrayList<>();
        contacto.add(contactos.get(position).getNombre());
        contacto.add(contactos.get(position).getTel());
        contacto.add(contactos.get(position).getFecNac());

        holder.elnombre.setText(contactos.get(position).getNombre());
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(contexto, InfoContactos.class);
                intent.putExtra(A, contacto);
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
            elnombre = itemView.findViewById(R.id.tvNombreContacto);
            parentlayout = itemView.findViewById(R.id.parentlayout);
        }


    }
}
