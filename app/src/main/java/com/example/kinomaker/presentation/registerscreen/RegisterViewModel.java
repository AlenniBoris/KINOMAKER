package com.example.kinomaker.presentation.registerscreen;

import androidx.lifecycle.ViewModel;

import com.example.kinomaker.domain.model.User;
import com.example.kinomaker.domain.usecase.RegisterUserUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

@HiltViewModel
public class RegisterViewModel extends ViewModel {

    private BehaviorSubject<RegisterStateHolder> state = BehaviorSubject.createDefault(new RegisterStateHolder());

    private RegisterUserUseCase useCase;

    @Inject
    public RegisterViewModel(
            RegisterUserUseCase registerUserUseCase
    ) {
        this.useCase = registerUserUseCase;
    }

    public Observable<RegisterStateHolder> getStateObservable() {
        return state.hide();
    }

    public void updateStateValue(String fieldName, String newValue) {
        RegisterStateHolder updatedState = state.getValue();
        switch (fieldName) {
            case "firstName": {
                updatedState.setFirstName(newValue);
            }
            case "lastName": {
                updatedState.setLastName(newValue);
            }
            case "email": {
                updatedState.setEmail(newValue);
            }
            case "phoneNumber": {
                updatedState.setPhoneNumber(newValue);
            }
            case "password": {
                updatedState.setPassword(newValue);
            }
            default: {
                updatedState.setErrorHappened(false);
                updatedState.setErrorText("");
            }
        }
        state.onNext(updatedState);
    }

    public void registerUser() {

        RegisterStateHolder currentState = state.getValue();

        User userToRegister = new User(
                currentState.getFirstName(),
                currentState.getLastName(),
                currentState.getEmail(),
                currentState.getPhoneNumber(),
                currentState.getPassword(),
                0,
                "",
                "",
                "",
                ""
        );
        useCase.invoke(userToRegister)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(@NonNull Boolean aBoolean) {

                        RegisterStateHolder updatedState = state.getValue();

                        updatedState.setUserWasRegistered(true);
                        state.onNext(updatedState);

                        disposable.dispose();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        RegisterStateHolder updatedState = state.getValue();

                        updatedState.setErrorHappened(true);
                        updatedState.setErrorText(e.getMessage());

                        state.onNext(updatedState);

                        disposable.dispose();
                    }
                });
    }

}
