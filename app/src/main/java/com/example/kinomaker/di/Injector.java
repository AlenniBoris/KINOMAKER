package com.example.kinomaker.di;

import com.example.kinomaker.data.database.ApplicationDatabase;

import org.checkerframework.checker.units.qual.A;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class Injector {

    @Provides
    @Singleton
    public ApplicationDatabase provideApplicationDatabase(){
        return new ApplicationDatabase();
    }

}
