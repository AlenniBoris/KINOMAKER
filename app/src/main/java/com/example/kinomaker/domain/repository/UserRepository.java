package com.example.kinomaker.domain.repository;

import com.example.kinomaker.domain.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import io.reactivex.rxjava3.core.Single;

public interface UserRepository {

    public Single<Boolean> loginUser(
            String email,
            String password
    );

    public Single<Boolean> registerUser(
            User user
    );

    public Single<User> getUserDataFromServer(
            String email
    );

    public void updateUserField(
            String email,
            String field,
            Object value,
            OnSuccessListener<Void> onSuccessListener,
            OnFailureListener onFailureListener
    );

}
