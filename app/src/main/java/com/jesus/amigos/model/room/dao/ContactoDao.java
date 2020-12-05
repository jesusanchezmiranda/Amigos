package com.jesus.amigos.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jesus.amigos.model.room.pojo.Contacto;
import com.jesus.amigos.model.room.pojo.LlamadasAmigo;

import java.util.List;

@Dao
public interface ContactoDao {

    @Delete
    int delete (Contacto cont);


    @Query("delete from contacto where id = :id")
    int delete (long id);

    @Query("select * from contacto where id = :id")
    Contacto get (int id);

    @Query("select * from contacto order by nombre")
    LiveData<List<Contacto>> getAll();

    @Query("select * from contacto order by id")
    List<Contacto> listAll();

    @Query("select c.id, c.nombre, c.tel, c.fecNac, count(l.id) count from contacto c left join llamada l on c.id = l.idAmigo group by c.id, c.nombre, c.tel, c.fecNac order by nombre")
    LiveData<List<LlamadasAmigo>> getAllLlamadas();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Contacto cont);

    @Update
    int update(Contacto cont);


    @Query("select id from contacto where tel = :tel")
    long selectIdFromLlamadaEntrante (String tel);


}
