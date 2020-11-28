package com.example.findme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PositionHelper extends SQLiteOpenHelper {
    public static String nom_fichier = "mespos.db";
    public static int ver = 1;

    public static final String table_pos = "Position";
    public static final String col_id = "Id";
    public static final String col_num = "numero";
    public static final String col_long = "Longitude";
    public static final String col_lat = "latitude";
    String req = "create table "+table_pos+
            "("+col_id+" integer primary key autoincrement,"+
            col_num+" Text not null," +
            col_long+" Text not null,"+
            col_lat+" Text not null)";
    public PositionHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+table_pos);
        onCreate(db);
    }
}
