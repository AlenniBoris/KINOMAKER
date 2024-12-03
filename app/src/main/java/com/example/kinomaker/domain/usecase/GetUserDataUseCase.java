package com.example.kinomaker.domain.usecase;

import com.example.kinomaker.domain.model.User;
import com.example.kinomaker.domain.repository.UserRepository;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class GetUserDataUseCase {

    private UserRepository repository;

    @Inject
    public GetUserDataUseCase(
            UserRepository userRepository
    ){
        this.repository = userRepository;
    }

    public Single<User> invoke(String email, String password){
        return repository.getUserDataFromServer(email);
    }

}
