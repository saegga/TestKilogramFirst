package com.example.sergei.testkilogramfirst.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by sergei on 07.11.2015.
 */
public class MusicContentProvider extends ContentProvider {

    MusicDataBase musicDataBase;

    public static final Uri URI = Uri.parse("content://com.example.sergei.testkilogramfirst.provider/baseMusic/music");
    public static final String AUTHORITY = MusicContract.CONTENT_AUTHORITY;
    public static final int ROUT_ENTRY = 1;
    public static final int ROUT_ENTRY_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "entries", ROUT_ENTRY);
        uriMatcher.addURI(AUTHORITY, "entries/*", ROUT_ENTRY_ID);
    }
    @Override
    public boolean onCreate() {
        musicDataBase = new MusicDataBase(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase base = musicDataBase.getReadableDatabase();


        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match){
            case ROUT_ENTRY: return MusicContract.Entry.CONTENT_TYPE;
            case ROUT_ENTRY_ID: return MusicContract.Entry.CONTENT_ITEM_TYPE;
            default: throw new UnsupportedOperationException("Unknow uri " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
    static class MusicDataBase extends SQLiteOpenHelper{
        private static final int VERSION = 1;
        private static final String DATA_BASE_NAME = "baseMusic.db";
        private static final String DATA_BASE_TABLE_MUSIC = "musics";

        public MusicDataBase(Context context) {
            super(context, DATA_BASE_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_SQL_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }

        private static final String CREATE_SQL_ENTRIES = "create table " +
                MusicContract.Entry.TABLE_NAME + " (" +
                MusicContract.Entry._ID + " integer primary key," +
                MusicContract.Entry.COLUMN_NAME_ENTRY_ID + " text," +
                MusicContract.Entry.COLUMN_NAME_AUTHOR + " text not null," +
                MusicContract.Entry.COLUMN_NAME_SONG_NAME + " text not null" + ");";

        private static final String DROP_TABLE = "drop table if exists " +
                MusicContract.Entry.TABLE_NAME;
    }
}
