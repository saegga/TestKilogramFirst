package com.example.sergei.testkilogramfirst.fragment;



import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.support.v4.content.Loader;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sergei.testkilogramfirst.provider.MusicContentProvider;
import com.example.sergei.testkilogramfirst.R;
import com.example.sergei.testkilogramfirst.adapter.GridCursorAdapter;
import com.example.sergei.testkilogramfirst.app.MyApplication;
import com.example.sergei.testkilogramfirst.db.MusicDataBaseHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by sergei on 06.11.2015.
 */
public class FragmentGrid extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {


    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String FETCH_URL = "http://tomcat.kilograpp.com/songs/api/songs";
    private static final String TAG = FragmentGrid.class.getSimpleName();
    private static final int LOADER_ID = 15;
    private GridCursorAdapter cursorAdapter;
    private MusicDataBaseHelper helper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        helper = new MusicDataBaseHelper(getActivity());
        getLoaderManager().initLoader(LOADER_ID, null, this);
//        cursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1,
//                null, new String[]{MusicDataBaseHelper.COLUMN_LABEL},new int[]{android.R.id.text1},
//                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        cursorAdapter = new GridCursorAdapter(getActivity(), null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        gridView.setAdapter(cursorAdapter);
        fetchMusic();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //fetchMusic();
    }

    @Override
     public void onRefresh() {
        fetchMusic();
    }// метод при обновлении списка

    private void fetchMusic(){
        swipeRefreshLayout.setRefreshing(true);
        final ArrayList<ContentProviderOperation> op = new ArrayList<>();
        final ContentValues values = new ContentValues();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(FETCH_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {
                Log.d(TAG, response.toString());
                if(response.length() > 0){
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            helper.clear();
                            JSONObject object = response.getJSONObject(i);

                            int id = object.getInt("id");
                            int version = object.getInt("version");
                            String label = object.getString("label");
                            String author = object.getString("author");
                            values.put(MusicDataBaseHelper.COLUMN_MUSIC_ID, id);
                            values.put(MusicDataBaseHelper.COLUMN_AUTHOR, author);
                            values.put(MusicDataBaseHelper.COLUMN_VERSION, version);
                            values.put(MusicDataBaseHelper.COLUMN_LABEL, label);
                            op.add(ContentProviderOperation.newInsert
                                    (MusicContentProvider.CONTENT_URI)
                                    .withValues(values)
                                    .build());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "Json Parser error" + e.getMessage());
                        }
                    }
                    try {
                        getActivity().getContentResolver().applyBatch(MusicContentProvider.AUTHORITY, op);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (OperationApplicationException e) {
                        e.printStackTrace();
                    }
                }

                swipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        MyApplication.getInstance().addToRequestQueue(jsonArrayRequest);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projections = {MusicDataBaseHelper.COLUMN_ID,MusicDataBaseHelper.COLUMN_AUTHOR, MusicDataBaseHelper.COLUMN_LABEL};
        CursorLoader cursorLoader = new CursorLoader(getActivity(), MusicContentProvider.CONTENT_URI,
                projections, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
