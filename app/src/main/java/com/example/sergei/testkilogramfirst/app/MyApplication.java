package com.example.sergei.testkilogramfirst.app;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

/**
 * Created by sergei on 08.11.2015.
 */
public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();
    public MyApplication() {
    }

    private static MyApplication application;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
    public static synchronized MyApplication getInstance(){
        return application;
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request, String tag){
        request.setTag(tag);
        getRequestQueue().add(request);
    }
    public <T> void addToRequestQueue(Request<T> request){
        request.setTag(TAG);
        getRequestQueue().add(request);
    }
    public void cancelPendingRequests(Objects tag){
        if(requestQueue != null){
            getRequestQueue().cancelAll(tag);
        }
    }
}
