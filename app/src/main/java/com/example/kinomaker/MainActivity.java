package com.example.kinomaker;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.kinomaker.navigation.Screen;
import com.github.terrakok.cicerone.Navigator;
import com.github.terrakok.cicerone.Router;
import com.github.terrakok.cicerone.androidx.AppNavigator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private Navigator navigator;
//    private MainActivityViewModel viewModel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigator = new AppNavigator(this, R.id.flActivityContainer);
//        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        viewModel.updateUserStatus(currentUser == null);

        if (savedInstanceState == null){
            if (currentUser == null){
                KinomakerApp.getRouter().newRootScreen(Screen.StartFragmentScreen("login", ""));
            } else {
                KinomakerApp.getRouter().newRootScreen(Screen.ApplicationFragmentScreen());
            }
        }

//        observeUserStatus();
    }

//    public void observeUserStatus(){
//        viewModel.getUserStatus()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Boolean>() {
//                    Disposable disposable;
//
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        disposable = d;
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Boolean status) {
//                        updateUI(status);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {}
//
//                    @Override
//                    public void onComplete() {
//                        disposable.dispose();
//                    }
//                });
//    }
//
//    public void updateUI(boolean status){
//        if (!status){
//            KinomakerApp.router.newRootScreen(Screen.StartFragmentScreen());
//        }
//    }

    @Override
    protected void onResume() {
        KinomakerApp.getCicerone().getNavigatorHolder().setNavigator(navigator);
        super.onResume();
    }

    @Override
    protected void onPause() {
        KinomakerApp.getCicerone().getNavigatorHolder().removeNavigator();
        super.onPause();
    }

}