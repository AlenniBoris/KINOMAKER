package com.example.kinomaker.di;

import com.example.kinomaker.data.repository.UserRepositoryImpl;
import com.example.kinomaker.domain.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public interface Binder {

    @Binds
    public UserRepository bindApplicationRepository(UserRepositoryImpl applicationRepositoryImpl);

}
