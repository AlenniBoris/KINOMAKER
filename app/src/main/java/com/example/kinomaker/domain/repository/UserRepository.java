package com.example.kinomaker.domain.repository;

import com.example.kinomaker.domain.model.User;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface UserRepository {

    Single<Boolean> loginUser(
            String email,
            String password
    );

    Single<Boolean> registerUser(
            User user
    );

    Single<User> getUserDataFromServer(
            String email
    );

    Completable updateUserField(
            String email,
            String field,
            Object value
    );

}
