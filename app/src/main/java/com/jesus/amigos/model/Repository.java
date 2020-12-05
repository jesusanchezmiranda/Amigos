package com.jesus.amigos.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.jesus.amigos.model.room.AmigosDatabase;
import com.jesus.amigos.model.room.dao.ContactoDao;
import com.jesus.amigos.model.room.dao.LlamadaDao;
import com.jesus.amigos.model.room.pojo.Contacto;
import com.jesus.amigos.model.room.pojo.Llamada;
import com.jesus.amigos.model.room.pojo.LlamadasAmigo;
import com.jesus.amigos.util.UtilThread;

import java.util.Calendar;
import java.util.List;

public class Repository {


    private ContactoDao contactoDao;
    private LlamadaDao llamadaDao;

    private LiveData<List<Contacto>> liveListaContactos;
    private LiveData<List<Llamada>> liveListaLlamadas;
    private LiveData<List<LlamadasAmigo>> liveLlamadasAmigo;


    public Repository(Context context) {

        AmigosDatabase db = AmigosDatabase.getDatabase(context);
        contactoDao = db.getContactoDao();
        llamadaDao = db.getLlamadaDao();
        liveListaContactos = contactoDao.getAll();
        liveLlamadasAmigo = contactoDao.getAllLlamadas();
        liveListaLlamadas = llamadaDao.getAll();

    }


    public LiveData<List<LlamadasAmigo>> getLiveLlamadasAmigo() {
        return liveLlamadasAmigo;
    }

    public void setLiveLlamadasAmigo(LiveData<List<LlamadasAmigo>> liveLlamadasAmigo) {
        this.liveLlamadasAmigo = liveLlamadasAmigo;
    }


    public LiveData<List<Llamada>> getLiveListaLlamadas() {
        return liveListaLlamadas;
    }

    public void setLiveListaLlamadas(LiveData<List<Llamada>> liveListaLlamadas) {
        this.liveListaLlamadas = liveListaLlamadas;
    }

    public LiveData<List<Contacto>> getLiveListaContactos() {
        return liveListaContactos;
    }

    public void insert(Contacto cont){

        UtilThread.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactoDao.insert(cont);
            }
        });

    }


    public void insert(Llamada llamada){

        UtilThread.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    llamadaDao.insert(llamada);
                }catch(Exception e){}
            }
        });

    }

    public void update(Contacto con){
        UtilThread.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactoDao.update(con);
            }
        });
    }

    public void delete(long id){
        UtilThread.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactoDao.delete(id);
            }
        });
    }

    public void selectIdFromLlamadaEntrante(String tel){

        UtilThread.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                long id = contactoDao.selectIdFromLlamadaEntrante(tel);
                Calendar c = Calendar.getInstance();
                int ano = c.get(Calendar.YEAR);
                int mes = c.get(Calendar.MONTH);
                int dia = c.get(Calendar.DAY_OF_MONTH);
                String fecha = dia + "/" + mes + "/" + ano;
                Llamada llamada = new Llamada(id, fecha);
                llamadaDao.insert(llamada);
            }
        });


    }

}
