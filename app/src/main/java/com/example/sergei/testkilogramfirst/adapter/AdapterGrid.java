package com.example.sergei.testkilogramfirst.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sergei.testkilogramfirst.R;
import com.example.sergei.testkilogramfirst.model.Music;

import java.util.List;

/**
 * Created by sergei on 06.11.2015.
 */
public class AdapterGrid extends BaseAdapter {
   private Context context;
    private List<Music> musicList;
    private LayoutInflater layoutInflater;

    public AdapterGrid(Context context, List<Music> musicList) {
        this.context = context;
        this.musicList = musicList;
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Object getItem(int position) {
        return musicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
        }
        Music music = (Music) getItem(position);
        TextView nameAuthor = (TextView) convertView.findViewById(R.id.nameAuthor);
        TextView songName = (TextView) convertView.findViewById(R.id.songName);
        nameAuthor.setText(music.getName());
        songName.setText(music.getAuthor());
        return convertView;
    }
}
