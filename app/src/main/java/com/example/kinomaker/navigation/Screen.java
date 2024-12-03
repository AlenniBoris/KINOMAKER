package com.example.kinomaker.navigation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.kinomaker.presentation.applicationscreen.ApplicationFragment;
import com.example.kinomaker.presentation.startscreen.StartFragment;
import com.github.terrakok.cicerone.androidx.FragmentScreen;

public class Screen {

    public static FragmentScreen StartFragmentScreen(){
        return new FragmentScreen() {
            @Override
            public boolean getClearContainer() {
                return false;
            }

            @NonNull
            @Override
            public Fragment createFragment(@NonNull FragmentFactory fragmentFactory) {
                StartFragment fragment = new StartFragment();
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
                return false;
            }

            @NonNull
            @Override
            public Fragment createFragment(@NonNull FragmentFactory fragmentFactory) {
                return new ApplicationFragment();
            }

            @NonNull
            @Override
            public String getScreenKey() {
                return "";
            }
        };
    }

}
