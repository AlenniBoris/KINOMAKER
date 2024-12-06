package com.example.kinomaker.presentation.applicationscreen;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.kinomaker.domain.model.User;
import com.example.kinomaker.domain.usecase.GetUserDataUseCase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

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
public class ApplicationFragmentViewModel extends ViewModel {

    private GetUserDataUseCase useCase;
    private BehaviorSubject<ApplicationFragmentStateHolder> state =
            BehaviorSubject.createDefault(new ApplicationFragmentStateHolder());

    public Observable<ApplicationFragmentStateHolder> getStateObservable(){
        return state.hide();
    }

    private void initAction(){
        ApplicationFragmentStateHolder currentState = state.getValue();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("EMAIL", user.getEmail());
        String signedInUserEmail = user.getEmail();
//
        currentState.setCurrentUserEmailFromFirebaseAuth(signedInUserEmail);
//        loadUserInfoFromServer();

        useCase.invoke(currentState.getCurrentUserEmailFromFirebaseAuth())
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
                        currentState.setCurrentUser(user);
                        currentState.setErrorHappened(false);
                        currentState.setErrorText("");

                        state.onNext(currentState);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        currentState.setErrorHappened(true);
                        currentState.setErrorText(e.getMessage());

                        state.onNext(currentState);
                        disposable.dispose();
                    }
                });

//        state.onNext(currentState);
    }

    public void loadUserInfoFromServer(){
        ApplicationFragmentStateHolder currentState = state.getValue();

        useCase.invoke(currentState.getCurrentUserEmailFromFirebaseAuth())
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
                        currentState.setCurrentUser(user);
                        currentState.setErrorHappened(false);
                        currentState.setErrorText("");

                        state.onNext(currentState);
                        disposable.dispose();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        currentState.setErrorHappened(true);
                        currentState.setErrorText(e.getMessage());

                        state.onNext(currentState);
                        disposable.dispose();
                    }
                });

    }

    @Inject
    public ApplicationFragmentViewModel(
          GetUserDataUseCase getUserDataUseCase
    ){
        this.useCase = getUserDataUseCase;
//        loadUserInfoFromServer();
        initAction();
    }

}
