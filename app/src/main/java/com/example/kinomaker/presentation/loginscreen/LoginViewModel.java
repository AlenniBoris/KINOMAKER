package com.example.kinomaker.presentation.loginscreen;


import androidx.lifecycle.ViewModel;

import com.example.kinomaker.domain.usecase.LoginUserUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private BehaviorSubject<LoginStateHolder> state = BehaviorSubject.createDefault(new LoginStateHolder());

    private LoginUserUseCase useCase;

    @Inject
    public LoginViewModel(
            LoginUserUseCase loginUserUseCase
    ) {
        this.useCase = loginUserUseCase;
    }

    public Observable<LoginStateHolder> getStateObservable() {
        return state.hide();
    }

    public void updateStateValue(String fieldName, String newValue) {
        LoginStateHolder updatedState = state.getValue();
        switch (fieldName) {
            case "enteredEmail": {
                updatedState.setEnteredEmail(newValue);
            }
            case "enteredPassword": {
                updatedState.setEnteredPassword(newValue);
            }
            default: {
                updatedState.setErrorText("");
                updatedState.setErrorHappened(false);
            }
        }
        state.onNext(updatedState);
    }

    public void loginUser(String email, String password) {
        useCase.invoke(email, password)
                .subscribe(new SingleObserver<Boolean>() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(@NonNull Boolean isAdded) {
                        LoginStateHolder updatedState = state.getValue();

                        updatedState.setUserIsAdded(isAdded);

                        state.onNext(updatedState);
                        disposable.dispose();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LoginStateHolder updatedState = state.getValue();

                        updatedState.setErrorHappened(true);
                        updatedState.setErrorText(e.getMessage());

                        state.onNext(updatedState);
                        disposable.dispose();
                    }
                });
    }

}
