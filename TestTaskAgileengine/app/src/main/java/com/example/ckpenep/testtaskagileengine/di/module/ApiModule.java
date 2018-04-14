package com.example.ckpenep.testtaskagileengine.di.module;

import com.example.ckpenep.testtaskagileengine.server.API;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ckpenep on 28.03.2018.
 */

@Module(includes = {RetrofitModule.class})
public class ApiModule {
    @Provides
    @Singleton
    public API provideApi(Retrofit retrofit) {
        return retrofit.create(API.class);
    }
}
