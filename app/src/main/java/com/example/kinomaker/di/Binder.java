package com.example.kinomaker.di;

import com.example.kinomaker.data.repository.ApplicationRepositoryImpl;
import com.example.kinomaker.domain.repository.ApplicationRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public interface Binder {

    @Binds
    public ApplicationRepository bindApplicationRepository(ApplicationRepositoryImpl applicationRepositoryImpl);

}
