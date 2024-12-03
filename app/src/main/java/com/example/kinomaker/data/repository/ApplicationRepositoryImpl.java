package com.example.kinomaker.data.repository;

import com.example.kinomaker.data.database.ApplicationDatabase;
import com.example.kinomaker.domain.repository.ApplicationRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private ApplicationDatabase database;

    @Inject
    public ApplicationRepositoryImpl(
            ApplicationDatabase database
    ){
        this.database = database;
    }

}
