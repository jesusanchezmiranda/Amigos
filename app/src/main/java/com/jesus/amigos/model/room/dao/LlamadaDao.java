package com.jesus.amigos.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jesus.amigos.model.room.pojo.Llamada;


import java.util.List;

@Dao
public interface LlamadaDao {

    @Delete
    int delete (Llamada llamada);


    @Query("delete from llamada where id = :id")
    int delete (long id);


    @Query("select * from llamada where id = :id")
    Llamada get (int id);


    @Query("select * from llamada")
    LiveData<List<Llamada>> getAll();

    @Query("select * from llamada ")
    List<Llamada> listAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Llamada llamada);

    @Update
    int update(Llamada llamada);

}
