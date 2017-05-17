package com.example.ch;

import android.app.Application;

import com.example.ch.component.DaggerNetComponent;
import com.example.ch.component.NetComponent;
import com.example.ch.module.AppModule;
import com.example.ch.module.NetModule;

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://ovz2.alexemfan.1xdez.vps.myjino.ru", this.getApplicationContext()))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}