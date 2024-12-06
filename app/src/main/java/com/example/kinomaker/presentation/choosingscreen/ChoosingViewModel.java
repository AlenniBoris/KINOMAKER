package com.example.kinomaker.presentation.choosingscreen;

import androidx.lifecycle.ViewModel;

import com.example.kinomaker.domain.usecase.UpdateUserDataUseCase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

@HiltViewModel
public class ChoosingViewModel extends ViewModel {

    private UpdateUserDataUseCase updateUserDataUseCase;

    private BehaviorSubject<ChoosingStateHolder> state =
            BehaviorSubject.createDefault(new ChoosingStateHolder());

    @Inject
    public ChoosingViewModel(
            UpdateUserDataUseCase updateUserDataUseCase
    ) {
        this.updateUserDataUseCase = updateUserDataUseCase;
    }

    public Observable<ChoosingStateHolder> getObservable() {
        return state.hide();
    }

    //0 - Search for job
    //1 - Search for workers
    public void setUserCompany(int value) {

        ChoosingStateHolder currentState = state.getValue();

        updateUserDataUseCase.invoke(
                Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail(),
                "company",
                String.valueOf(value)
        ).subscribe(new CompletableObserver() {

            Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onComplete() {
                currentState.setTaskCompleted(true);
                currentState.setErrorHappened(false);
                currentState.setErrorMessage("");
                state.onNext(currentState);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                currentState.setErrorHappened(true);
                currentState.setErrorMessage(e.getMessage());
                state.onNext(currentState);
                disposable.dispose();
            }
        });
    }
}
