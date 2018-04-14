package com.example.ckpenep.testtaskagileengine.di;

import android.content.Context;

import com.example.ckpenep.testtaskagileengine.di.module.ContextModule;
import com.example.ckpenep.testtaskagileengine.di.module.LocalNavigationModule;
import com.example.ckpenep.testtaskagileengine.di.module.NavigationModule;
import com.example.ckpenep.testtaskagileengine.di.module.StackoverflowModule;
import com.example.ckpenep.testtaskagileengine.ui.activities.login.LoginActivity;
import com.example.ckpenep.testtaskagileengine.ui.activities.main.MainActivity;
import com.example.ckpenep.testtaskagileengine.ui.fragments.question.QuestionPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ckpenep on 23.03.2018.
 */

@Singleton
@Component(modules = {
        ContextModule.class,
        NavigationModule.class,
        LocalNavigationModule.class,
        StackoverflowModule.class
})
public interface AppComponent {

    Context getContext();

    void inject(LoginActivity mLoginActivity);
    void inject(QuestionPresenter questionPresenter);
    void inject(MainActivity mainActivity);

}
