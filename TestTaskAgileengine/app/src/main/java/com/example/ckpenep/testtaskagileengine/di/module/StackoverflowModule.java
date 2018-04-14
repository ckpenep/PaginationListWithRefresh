package com.example.ckpenep.testtaskagileengine.di.module;

import com.example.ckpenep.testtaskagileengine.providers.StackoverflowService;
import com.example.ckpenep.testtaskagileengine.server.API;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ckpenep on 28.03.2018.
 */

@Module(includes = {ApiModule.class})
public class StackoverflowModule {
    @Provides
    @Singleton
    public StackoverflowService provideGithubService(API api) {
        return new StackoverflowService(api);
    }
}
