package com.jesus.amigos.model.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Llamada")
public class Llamada {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "idAmigo")
    private long idAmigo;

    @NonNull
    @ColumnInfo(name = "fecLlamada")
    private String fecLlamada;


    public Llamada(@NonNull long idAmigo, @NonNull String fecLlamada) {
        this.idAmigo = idAmigo;
        this.fecLlamada = fecLlamada;
    }


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public long getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(@NonNull Long idAmigo) {
        this.idAmigo = idAmigo;
    }

    @NonNull
    public String getFecLlamada() {
        return fecLlamada;
    }

    public void setFecLlamada(@NonNull String fecLlamada) {
        this.fecLlamada = fecLlamada;
    }

    @Override
    public String toString() {
        return "idAmigo=" + idAmigo + ", fecLlamada='" + fecLlamada + '\'';
    }
}

