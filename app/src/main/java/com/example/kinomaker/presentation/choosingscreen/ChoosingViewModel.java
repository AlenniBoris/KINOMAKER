package com.example.kinomaker.presentation.choosingscreen;

import androidx.lifecycle.ViewModel;

import com.example.kinomaker.domain.model.User;
import com.example.kinomaker.domain.usecase.GetUserDataUseCase;
import com.example.kinomaker.domain.usecase.UpdateUserDataUseCase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Closeable;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_components_SingletonComponent;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

@HiltViewModel
public class ChoosingViewModel extends ViewModel {

    private GetUserDataUseCase getUserDataUseCase;
    private UpdateUserDataUseCase updateUserDataUseCase;

    private BehaviorSubject<ChoosingStateHolder> state =
            BehaviorSubject.createDefault(new ChoosingStateHolder());

    @Inject
    public ChoosingViewModel(
            GetUserDataUseCase getUserDataUseCase,
            UpdateUserDataUseCase updateUserDataUseCase
    ) {
        this.getUserDataUseCase = getUserDataUseCase;
        this.updateUserDataUseCase = updateUserDataUseCase;

        getUserData();
    }

    public Observable<ChoosingStateHolder> getObservable(){
        return state.hide();
    }

    //0 - Search for job
    //1 - Search for workers
    public void setUserCompany(int value){

        ChoosingStateHolder currentState = state.getValue();

        updateUserDataUseCase.invoke(
                currentState.getUser().getEmail(),
                "isCompany",
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
                disposable.dispose();
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

    private void getUserData(){
        String email = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();

        ChoosingStateHolder currentState = state.getValue();

        getUserDataUseCase.invoke(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(@NonNull User user) {
                        currentState.setUser(user);
                        state.onNext(currentState);
                        disposable.dispose();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        disposable.dispose();
                    }
                });

    }


}
