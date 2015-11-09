package com.example.sergei.testkilogramfirst.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sergei.testkilogramfirst.R;
import com.example.sergei.testkilogramfirst.adapter.AdapterGrid;
import com.example.sergei.testkilogramfirst.app.MyApplication;
import com.example.sergei.testkilogramfirst.model.Music;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei on 06.11.2015.
 */
public class FragmentGrid extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<Music> musicList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String FETCH_URL = "http://tomcat.kilograpp.com/songs/api/songs";
    private static final String TAG = FragmentGrid.class.getSimpleName();

    AdapterGrid adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        musicList = new ArrayList<>();
        fetchMusic();
        adapter = new AdapterGrid(getActivity(), musicList);

        gridView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
     public void onRefresh() {
        fetchMusic();
    }

    private void fetchMusic(){
        musicList.clear();
        swipeRefreshLayout.setRefreshing(true);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(FETCH_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                if(response.length() > 0){
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            int id = object.getInt("id");
                            String label = object.getString("label");
                            String author = object.getString("author");
                            Music music = new Music(id, author, label);
                            musicList.add(music);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "Json Parser error" + e.getMessage());
                        }
                    }
                    adapter.notifyDataSetChanged();
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
}
