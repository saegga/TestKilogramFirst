package com.example.sergei.testkilogramfirst.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.sergei.testkilogramfirst.account.Authenticator;

/**
 * Created by sergei on 07.11.2015.
 */
public class AuthenticatorService extends Service {

    private  Authenticator authenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        authenticator = new Authenticator(this);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return authenticator.getIBinder();
    }
}
