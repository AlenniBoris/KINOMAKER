package com.example.kinomaker.domain.usecase;

import com.example.kinomaker.domain.model.User;
import com.example.kinomaker.domain.repository.UserRepository;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
public class RegisterUserUseCase {

    private UserRepository repository;

    @Inject
    public RegisterUserUseCase(
            UserRepository userRepository
    ) {
        this.repository = userRepository;
    }

    public Single<Boolean> invoke(User userToRegister) {
        return repository.registerUser(userToRegister);
    }

}
