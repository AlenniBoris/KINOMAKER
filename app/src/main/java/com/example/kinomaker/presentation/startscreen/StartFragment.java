package com.example.kinomaker.presentation.startscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kinomaker.R;
import com.example.kinomaker.databinding.FragmentStartBinding;
import com.example.kinomaker.presentation.loginscreen.LoginFragment;
import com.example.kinomaker.presentation.registerscreen.RegisterFragment;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class StartFragment extends Fragment {

    private FragmentStartBinding binding;
    private String neededScreen;
    private String userLogin;

    public StartFragment() {}

    public static StartFragment newInstance() {
        return new StartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null){
            neededScreen = getArguments() == null ? "" : getArguments().getString("needed_screen_to_show");
            Log.d("START_SCREEN", neededScreen.toString());
            userLogin = getArguments() == null ? "" : getArguments().getString("user_registered_login");

            if (neededScreen.equals("login")){

                Bundle loginBundle = new Bundle();
                loginBundle.putString("user_login", userLogin);

                LoginFragment loginFragment = new LoginFragment();
                loginFragment.setArguments(loginBundle);

                getParentFragmentManager().beginTransaction()
                        .replace(
                                binding.flStartFragmentContainer.getId(),
                                loginFragment,
                                "StartFragment"
                        )
                        .commit();
            }
            else{
                getParentFragmentManager().beginTransaction()
                        .replace(
                                binding.flStartFragmentContainer.getId(),
                                new RegisterFragment(),
                                "StartFragment"
                        )
                        .commit();
            }
        }
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}