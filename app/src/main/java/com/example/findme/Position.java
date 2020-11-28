package com.example.findme;

public class Position {
    int id;
    String numero,longitude,latitude;
    Position(int id,String numero, String longitude,String latitude){
        this.numero = numero;
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = id;
    }
}
