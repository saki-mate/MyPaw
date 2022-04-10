package com.example.projekat.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PasDao {
    @Query("SELECT * FROM pas")
    List<Pas> getAllPas();

    @Insert
    void insertPas(Pas... psi);

    @Delete
    void delete(Pas pas);

    @Delete
    void reset(List<Pas> psi);

    @Query("DELETE FROM Pas")
    void deleteAll();

    //jos za izmenu pojedinacnog
    //@Query("UPDATE table_name SET text = :sText WHERE ID:=sID)
    //void update(int sID, String sText);
}
