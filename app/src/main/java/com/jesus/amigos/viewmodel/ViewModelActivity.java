package com.jesus.amigos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.jesus.amigos.model.Repository;
import com.jesus.amigos.model.room.pojo.Contacto;
import com.jesus.amigos.model.room.pojo.Llamada;
import com.jesus.amigos.model.room.pojo.LlamadasAmigo;

import java.util.ArrayList;
import java.util.List;

public class ViewModelActivity extends AndroidViewModel {

    private Repository repository;
    private List<Contacto> listaContactos = new ArrayList<>();

    public ViewModelActivity(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public List<Contacto> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(Contacto contacto) {
       listaContactos.add(contacto);
    }


    public LiveData<List<Contacto>> getLiveListaContactos() {
        return repository.getLiveListaContactos();
    }

    public void insert(Contacto cont) {
        repository.insert(cont);
    }

    public void insert(Llamada llamada) {
        repository.insert(llamada);
    }

    public void update(Contacto con) {
        repository.update(con);
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public LiveData<List<LlamadasAmigo>> getLiveLlamadasAmigoList() {
        return repository.getLiveLlamadasAmigo();
    }

    public void selectIdFromLlamadaEntrante(String tel) {
        repository.selectIdFromLlamadaEntrante(tel);
    }

    public LiveData<List<Llamada>> getLiveListaLlamadas() {
        return repository.getLiveListaLlamadas();
    }

}
