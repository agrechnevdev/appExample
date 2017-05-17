package com.example.ch.component;

import com.example.ch.activity.CoreActivity;
import com.example.ch.activity.MainActivity;
import com.example.ch.activity.RegisterActivity;
import com.example.ch.fragment.ChallengesFragment;
import com.example.ch.module.AppModule;
import com.example.ch.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
    void inject(ChallengesFragment activity);
    void inject(RegisterActivity activity);
    void inject(CoreActivity activity);

}