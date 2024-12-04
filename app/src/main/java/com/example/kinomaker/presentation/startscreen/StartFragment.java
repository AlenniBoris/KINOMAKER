package com.example.kinomaker.presentation.startscreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kinomaker.databinding.FragmentStartBinding;
import com.example.kinomaker.presentation.loginscreen.views.LoginFragment;
import com.example.kinomaker.presentation.registerscreen.views.RegisterFragment;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class StartFragment extends Fragment {

    private FragmentStartBinding binding;
    private String neededScreen;
    private String userEmail;

    public StartFragment() {
    }

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

        if (savedInstanceState == null) {
            neededScreen = getArguments() == null ? "" : getArguments().getString("needed_screen_to_show");
            Log.d("START_SCREEN", neededScreen.toString());
            userEmail = getArguments() == null ? "" : getArguments().getString("user_entered_login");

            Bundle bundle = new Bundle();
            bundle.putString("user_login", userEmail);

            if (neededScreen.equals("login")) {

                LoginFragment loginFragment = new LoginFragment();
                loginFragment.setArguments(bundle);

                getParentFragmentManager().beginTransaction()
                        .replace(
                                binding.flStartFragmentContainer.getId(),
                                loginFragment,
                                "StartFragment"
                        )
                        .commit();
            } else {

                RegisterFragment registerFragment = new RegisterFragment();
                registerFragment.setArguments(bundle);

                getParentFragmentManager().beginTransaction()
                        .replace(
                                binding.flStartFragmentContainer.getId(),
                                registerFragment,
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