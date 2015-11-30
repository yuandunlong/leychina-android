package com.leychina.activity;

import android.app.Application;

import com.leychina.manager.PrefManager;
import com.leychina.model.User;

/**
 * Created by yuandunlong on 11/15/15.
 */
public class App extends Application {

    public static volatile User user;

    @Override
    public void onCreate() {
        super.onCreate();
        PrefManager.initPrefManager(getApplicationContext(), "leychina");
        user=new User();
        user.loadFromPref();

    }
}
