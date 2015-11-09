package com.example.sergei.testkilogramfirst.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by sergei on 08.11.2015.
 */
public class MusicContract {

    public static final String CONTENT_AUTHORITY = "com.example.sergei.testkilogramfirst";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ENTRIES = "entries";

    public static class Entry implements BaseColumns{

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.testkilogramfirst.entries";

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.testkilogramfirst.entry";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ENTRIES).build();

        public static final String TABLE_NAME = "entry";

        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_SONG_NAME = "song";
    }
}
