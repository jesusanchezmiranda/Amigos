package com.jesus.amigos.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jesus.amigos.model.room.dao.ContactoDao;
import com.jesus.amigos.model.room.dao.LlamadaDao;
import com.jesus.amigos.model.room.pojo.Contacto;
import com.jesus.amigos.model.room.pojo.Llamada;


@Database(entities = {Contacto.class, Llamada.class}, version = 1, exportSchema = false)
public abstract class AmigosDatabase extends RoomDatabase {


    public abstract ContactoDao getContactoDao();
    public abstract LlamadaDao getLlamadaDao();

    private static volatile AmigosDatabase INSTANCE;

    public static AmigosDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AmigosDatabase.class, "dbAmigos.sqlite").build();
                }
        return INSTANCE;
    }

}
