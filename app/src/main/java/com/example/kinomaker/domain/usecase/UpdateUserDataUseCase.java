package com.example.kinomaker.domain.usecase;

import com.example.kinomaker.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class UpdateUserDataUseCase {

    private UserRepository userRepository;

    @Inject
    public UpdateUserDataUseCase(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public Completable invoke(String email, String field, Object value) {
        return userRepository.updateUserField(email, field, value);
    }

}
