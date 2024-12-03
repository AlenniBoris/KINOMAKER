package com.example.kinomaker.domain.usecase;

import com.example.kinomaker.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

@Singleton
public class LoginUserUseCase {

    private UserRepository repository;

    @Inject
    public LoginUserUseCase(
            UserRepository userRepository
    ){
        this.repository = userRepository;
    }

    public Single<Boolean> invoke(String email, String password){
        return repository.loginUser(email, password);
    }

}
