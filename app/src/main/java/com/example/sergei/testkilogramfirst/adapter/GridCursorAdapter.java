package com.example.sergei.testkilogramfirst.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.sergei.testkilogramfirst.R;

/**
 * Created by sergei on 23.11.2015.
 */
public class GridCursorAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;
    public GridCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public GridCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.grid_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameAuthor = (TextView) view.findViewById(R.id.nameAuthor);
        TextView nameSong = (TextView) view.findViewById(R.id.songName);
        String author = cursor.getString(1);
        String song = cursor.getString(2);
        nameAuthor.setText(author);
        nameSong.setText(song);
    }
}
