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

    private static final int VERSION = 1;
    private static final String DB_NAME = "music.sqlite";
    public static final String TABLE_MUSIC = "music";
    private static final String COLUMN_LABEL = "label";
    private static final String COLUMN_ID = "music_id";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_VERSION = "version";


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
        db.execSQL(DELETE_TABLE_MUSIC);
        onCreate(db);
    }
    private static final String CREATE_TABLE_MUSIC = "create table music (" +
            " _id integer primary key, music_id integer, label text not null, author text not null, version integer);";

    private static final String DELETE_TABLE_MUSIC = "drop table music if exists";

    public long insertMusic(Music music){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, music.getId());
        contentValues.put(COLUMN_LABEL, music.getLabel());
        contentValues.put(COLUMN_AUTHOR, music.getAuthor());
        contentValues.put(COLUMN_VERSION, music.getVersion());
        return getWritableDatabase().insert(TABLE_MUSIC, null, contentValues);
    }

    public long deleteMusic(String whereClause,String[] whereArgs){
        return getWritableDatabase().delete(TABLE_MUSIC, whereClause, whereArgs);
    }
}


