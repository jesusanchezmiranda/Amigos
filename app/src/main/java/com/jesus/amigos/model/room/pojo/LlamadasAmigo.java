package com.jesus.amigos.model.room.pojo;

import androidx.room.Embedded;

public class LlamadasAmigo {

    @Embedded
    private Contacto contacto;

    private long count;

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "LlamadasAmigo{" +
                "contacto=" + contacto +
                ", count=" + count +
                '}';
    }
}
