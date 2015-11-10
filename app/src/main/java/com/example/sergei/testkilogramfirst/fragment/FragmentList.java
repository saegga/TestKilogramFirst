package com.example.sergei.testkilogramfirst.fragment;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.sergei.testkilogramfirst.R;
import com.example.sergei.testkilogramfirst.SqLiteCursorLoader;
import com.example.sergei.testkilogramfirst.db.MusicDataBaseHelper;
import com.example.sergei.testkilogramfirst.model.Music;


/**
 * Created by sergei on 09.11.2015.
 */
public class FragmentList extends ListFragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "FragmentList";
    private static MusicDataBaseHelper dbHelper;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view  = inflater.inflate(R.layout.fragment_list_test, container, false);
//
//        return view;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MusicCursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        MusicCursorAdapter musicCursorAdapter = new MusicCursorAdapter(getActivity(), data, 0);
        setListAdapter(musicCursorAdapter);
    }


    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        setListAdapter(null);
    }


    private static class MusicCursorLoader extends SqLiteCursorLoader{

        public MusicCursorLoader(Context context) {
            super(context);
        }

        @Override
        protected Cursor loadCursor() {
            dbHelper = new MusicDataBaseHelper(getContext());
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            return database.query(MusicDataBaseHelper.TABLE_MUSIC, null, null,null,null,null,null);
        }
    }
    private static class MusicCursorAdapter extends CursorAdapter{

        private Cursor cursor;
        public MusicCursorAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
            this.cursor = c;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            int id = cursor.getInt(1);
            String label = cursor.getString(2);
            String author = cursor.getString(3);
            int version = cursor.getInt(4);
            TextView testView = (TextView) view;
            Log.d(TAG, label);
            testView.setText(label);
        }
    }
    //todo сделать сетевой запрос на получение данных положить в бд и вывести список из бд
}