package com.example.ckpenep.testtaskagileengine;

import android.app.Application;

import com.example.ckpenep.testtaskagileengine.di.AppComponent;
import com.example.ckpenep.testtaskagileengine.di.DaggerAppComponent;
import com.example.ckpenep.testtaskagileengine.di.module.ContextModule;

/**
 * Created by ckpenep on 23.03.2018.
 */

public class App extends Application {

    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }
}
