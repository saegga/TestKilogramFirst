package com.example.sergei.testkilogramfirst;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by sergei on 09.11.2015.
 */
public abstract class SqLiteCursorLoader extends android.support.v4.content.AsyncTaskLoader<Cursor> {

    private Cursor cursor;

    protected abstract Cursor loadCursor();
    public SqLiteCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = loadCursor();
        if(cursor != null){
            cursor.getCount();
        }
        return cursor;
    }

    @Override
    protected void onStartLoading() {
        if(cursor != null){
            deliverResult(cursor);
        }
        if(takeContentChanged() || cursor == null){
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void deliverResult(Cursor data) {
        Cursor oldCursor = cursor;
        this.cursor = data;
        if(isStarted()){
            super.deliverResult(data);
        }
        if(oldCursor != null && oldCursor != data && !oldCursor.isClosed()){
            oldCursor.close();
        }
    }

    @Override
    public void onCanceled(Cursor data) {
        if(cursor != null && !cursor.isClosed()){
            cursor.close();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if(cursor != null && !cursor.isClosed()){
            cursor.close();
        }
        cursor = null;
    }
}
