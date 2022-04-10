package com.example.projekat.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pas {
    @PrimaryKey(autoGenerate = true)
    public int pk;

    @ColumnInfo(name = "ime")
    public String ime;

    @ColumnInfo(name = "rasa")
    public String rasa;

    @ColumnInfo(name = "datumRodjenja")
    public String datumRodjenja;

    @ColumnInfo(name = "tezina")
    public String tezina;

    @ColumnInfo(name = "slicica")
    public String slicica;

    @ColumnInfo(name = "pol")
    public String pol;

}
