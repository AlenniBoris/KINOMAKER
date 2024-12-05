package com.example.kinomaker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigator = new AppNavigator(this, R.id.flActivityContainer);

//        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
//        String signedInEmail = sharedPreferences.getString("signed_in_email", "");
//        viewModel.updateCurrentUser(signedInEmail);
//        Log.d("STARTING_USER", "onCreate = "+signedInEmail);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (savedInstanceState == null) {
            if (user == null) {
                KinomakerApp.getRouter().newRootScreen(Screen.StartFragmentScreen("login", ""));
            } else {
                Toast.makeText(
                        this,
                        "Welcome back, " + user.getEmail(),
                        Toast.LENGTH_SHORT
                ).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
//
//        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("signed_in_email", viewModel.getCurrentUserEmail());
//        editor.apply();

    }
}