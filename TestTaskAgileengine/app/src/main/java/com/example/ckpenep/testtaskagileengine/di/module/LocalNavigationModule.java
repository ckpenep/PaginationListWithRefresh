package com.example.ckpenep.testtaskagileengine.di.module;

import com.example.ckpenep.testtaskagileengine.subnavigation.LocalCiceroneHolder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ckpenep on 23.03.2018.
 */

@Module
public class LocalNavigationModule {

    @Provides
    @Singleton
    LocalCiceroneHolder provideLocalNavigationHolder() {
        return new LocalCiceroneHolder();
    }
}
