package com.example.kinomaker.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.kinomaker.presentation.applicationscreen.ApplicationFragment;
import com.example.kinomaker.presentation.startscreen.StartFragment;
import com.github.terrakok.cicerone.androidx.FragmentScreen;

public class Screen {

    public static FragmentScreen StartFragmentScreen(String neededScreen, String userEmail){
        return new FragmentScreen() {
            @Override
            public boolean getClearContainer() {
                return true;
            }

            @NonNull
            @Override
            public Fragment createFragment(@NonNull FragmentFactory fragmentFactory) {
                StartFragment fragment = new StartFragment();

                Bundle bundle = new Bundle();
                bundle.putString("needed_screen_to_show", neededScreen);
                bundle.putString("user_entered_email", userEmail);

                fragment.setArguments(bundle);

                return fragment;
            }

            @NonNull
            @Override
            public String getScreenKey() {
                return "";
            }
        };
    }

    public static FragmentScreen ApplicationFragmentScreen(){
        return new FragmentScreen() {
            @Override
            public boolean getClearContainer() {
                return true;
            }

            @NonNull
            @Override
            public Fragment createFragment(@NonNull FragmentFactory fragmentFactory) {
                ApplicationFragment fragment = new ApplicationFragment();
                return fragment;
            }

            @NonNull
            @Override
            public String getScreenKey() {
                return "";
            }
        };
    }

}
