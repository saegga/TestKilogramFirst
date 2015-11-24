package com.example.sergei.testkilogramfirst.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sergei.testkilogramfirst.model.Music;

/**
 * Created by sergei on 09.11.2015.
 */
public class MusicDataBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String DB_NAME = "music.db";
    public static final String TABLE_MUSIC = "musics";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LABEL = "label";
    public static final String COLUMN_MUSIC_ID = "music_id";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_VERSION = "version";


    public MusicDataBaseHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }
    public MusicDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MUSIC);
        Log.d("MusicDataBaseHelper", "create db" + db.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(VERSION < newVersion){
            db.execSQL(DELETE_TABLE_MUSIC);
            onCreate(db);
        }

    }
    private static final String CREATE_TABLE_MUSIC = "create table " + TABLE_MUSIC + " (" + COLUMN_ID +
            " integer primary key autoincrement, " +  COLUMN_MUSIC_ID + " integer, " + COLUMN_LABEL +" text, "
            + COLUMN_AUTHOR +" text, " + COLUMN_VERSION + " integer)";

    private static final String DELETE_TABLE_MUSIC = "drop table if exists " + TABLE_MUSIC;

    public void clear(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MUSIC, COLUMN_ID + " > ?", new String[]{String.valueOf(0)});
        db.close();
    }
}


