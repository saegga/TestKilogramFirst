package com.example.sergei.testkilogramfirst.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.sergei.testkilogramfirst.R;
import com.example.sergei.testkilogramfirst.fragment.FragmentGrid;

/**
 * Created by sergei on 06.11.2015.
 */
public class MainActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if(fragment == null){
            fragment = new FragmentGrid();
            //fragment = new FragmentList();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
