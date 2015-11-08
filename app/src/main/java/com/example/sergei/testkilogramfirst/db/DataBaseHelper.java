package com.example.sergei.testkilogramfirst.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import com.example.sergei.testkilogramfirst.model.Music;

/**
 * Created by sergei on 06.11.2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATA_BASE_NAME = "baseMusic.db";
    private static final String DATA_BASE_TABLE_MUSIC = "musics";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AUTHOR = "author";


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    public DataBaseHelper(Context context){
        super(context, DATA_BASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_BASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

//    public long insertMusic(Music music){
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_ID, music.getId());
//        values.put(COLUMN_NAME, music.getName());
//        values.put(COLUMN_AUTHOR, music.getAuthor());
//        return getWritableDatabase().insert(DATA_BASE_TABLE_MUSIC, null, values);
//    }

    private static final String SQL_CREATE_BASE = "create table " + DATA_BASE_TABLE_MUSIC
            + " (" + SyncStateContract.Columns._ID + " integer primary key," + COLUMN_NAME + " text not null,"
            + COLUMN_AUTHOR + "text not null);";
}
