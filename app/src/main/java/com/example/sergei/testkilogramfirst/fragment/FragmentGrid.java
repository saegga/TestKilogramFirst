package com.example.sergei.testkilogramfirst.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.sergei.testkilogramfirst.R;
import com.example.sergei.testkilogramfirst.adapter.AdapterGrid;
import com.example.sergei.testkilogramfirst.model.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei on 06.11.2015.
 */
public class FragmentGrid extends Fragment {

    private List<Music> musicList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        ini();
        AdapterGrid adapter = new AdapterGrid(getActivity(), musicList);
        gridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void ini(){
        musicList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Music music = new Music(i + "", "author", "song name");
            musicList.add(music);
        }
    }
}
