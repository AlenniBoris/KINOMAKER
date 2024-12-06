package com.example.kinomaker.presentation.loginscreen.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.kinomaker.databinding.FragmentLoginBinding;
import com.example.kinomaker.di.KinomakerApp;
import com.example.kinomaker.navigation.Screen;
import com.example.kinomaker.presentation.loginscreen.LoginStateHolder;
import com.example.kinomaker.presentation.loginscreen.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;
    private String comingEmail;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        comingEmail = getArguments().getString("user_login", "");

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!comingEmail.isEmpty()) {
            binding.etEmail.setText(comingEmail);
            viewModel.updateStateValue("enteredEmail", comingEmail);
        }

        binding.etEmail.addTextChangedListener(getTextWatcher("enteredEmail"));

        binding.etPassword.addTextChangedListener(getTextWatcher("enteredPassword"));

        observeScreenState();
    }

    private TextWatcher getTextWatcher(String aim) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.updateStateValue(aim, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public void observeScreenState() {
        viewModel.getStateObservable()
                .subscribe(new Observer<LoginStateHolder>() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(LoginStateHolder state) {
                        updateUi(state);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR RXJAVA", "ERROR LOGIN SCREEN: \n" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }

    public void updateUi(LoginStateHolder state) {
        if (state.isUserIsAdded()) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(state.getEnteredEmail(), state.getEnteredPassword())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(
                                    requireActivity().getApplicationContext(),
                                    "Welcome, " + state.getEnteredEmail(),
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    });

            KinomakerApp.getRouter().newRootScreen(Screen.WorkingFragmentScreen());
            return;
        }
        if (state.isErrorHappened()) {
            Toast.makeText(
                    requireContext(),
                    state.getErrorText(),
                    Toast.LENGTH_SHORT
            ).show();

            viewModel.updateStateValue("", "");
        }

        binding.btnSignIn.setOnClickListener(v -> {
            viewModel.loginUser(state.getEnteredEmail(), state.getEnteredPassword());
        });

        binding.btnRegister.setOnClickListener(v -> {
            KinomakerApp.getRouter().newRootScreen(Screen.StartFragmentScreen(
                    "register",
                    state.getEnteredEmail()
            ));
        });


    }
}