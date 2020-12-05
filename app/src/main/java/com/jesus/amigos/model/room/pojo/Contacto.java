package com.jesus.amigos.model.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contacto", indices = {@Index(value = {"tel"}, unique = true)})
public class Contacto {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "tel")
    private String tel;

    @NonNull
    @ColumnInfo(name = "fecNac")
    private String fecNac;

    public Contacto() {

    }

    public Contacto(@NonNull String nombre, @NonNull String tel, @NonNull String fecNac) {
        this.nombre = nombre;
        this.tel = tel;
        this.fecNac = fecNac;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getTel() {
        return tel;
    }

    public void setTel(@NonNull String tel) {
        this.tel = tel;
    }

    @NonNull
    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(@NonNull String fecNac) {
        this.fecNac = fecNac;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tel='" + tel + '\'' +
                ", fecNac='" + fecNac + '\'' +
                '}';
    }
}
