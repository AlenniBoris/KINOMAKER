package com.example.kinomaker;


import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class MainActivityViewModel extends ViewModel {

    private BehaviorSubject<Boolean> userStatus = BehaviorSubject.createDefault(false);

    public Observable<Boolean> getUserStatus(){
        return userStatus.hide();
    }

    public void updateUserStatus(boolean newStatus){
        userStatus.onNext(newStatus);
    }

    public MainActivityViewModel(){}


}
