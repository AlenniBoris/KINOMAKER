package com.example.kinomaker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kinomaker.di.KinomakerApp;
import com.example.kinomaker.navigation.Screen;
import com.github.terrakok.cicerone.Navigator;
import com.github.terrakok.cicerone.androidx.AppNavigator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private Navigator navigator;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigator = new AppNavigator(this, R.id.flActivityContainer);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (savedInstanceState == null) {
            if (currentUser == null) {
                KinomakerApp.getRouter().newRootScreen(Screen.StartFragmentScreen("login", ""));
            } else {
                KinomakerApp.getRouter().newRootScreen(Screen.ApplicationFragmentScreen());
            }
        }

    }

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