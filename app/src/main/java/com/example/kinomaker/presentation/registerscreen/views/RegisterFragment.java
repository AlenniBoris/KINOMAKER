package com.example.kinomaker.presentation.registerscreen.views;

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

import com.example.kinomaker.databinding.FragmentRegisterBinding;
import com.example.kinomaker.di.KinomakerApp;
import com.example.kinomaker.navigation.Screen;
import com.example.kinomaker.presentation.registerscreen.RegisterStateHolder;
import com.example.kinomaker.presentation.registerscreen.RegisterViewModel;
import com.google.firebase.auth.FirebaseAuth;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

@AndroidEntryPoint
public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;
    private String comingEmail;

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        comingEmail = getArguments().getString("user_login", "");

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.etName.addTextChangedListener(getTextWatcher("firstName"));
        binding.etSurname.addTextChangedListener(getTextWatcher("lastName"));
        binding.etEmail.addTextChangedListener(getTextWatcher("email"));
        binding.etPhoneNumbber.addTextChangedListener(getTextWatcher("phoneNumber"));
        binding.etPassword.addTextChangedListener(getTextWatcher("password"));

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
                .subscribe(new Observer<RegisterStateHolder>() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(RegisterStateHolder state) {
                        updateUi(state);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR RXJAVA", "ERROR REGISTER SCREEN: \n" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }

    public void updateUi(RegisterStateHolder state) {
        if (state.isUserWasRegistered()) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(state.getEmail(), state.getPassword());
            mAuth.signInWithEmailAndPassword(state.getEmail(), state.getPassword())
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(
                                            requireActivity().getApplicationContext(),
                                            "Welcome, " + state.getEmail(),
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            });
            KinomakerApp.getRouter().newRootScreen(Screen.ChoosingFragmentScreen());
            return;
        }

        if (state.getErrorHappened()) {
            Toast.makeText(
                    requireContext(),
                    state.getErrorText(),
                    Toast.LENGTH_SHORT
            ).show();

            viewModel.updateStateValue("", "");
        }

        binding.btnRegister.setOnClickListener(v -> {
            viewModel.registerUser();
        });

        binding.btnSignIn.setOnClickListener(v -> {
            KinomakerApp.getRouter().newRootScreen(
                    Screen.StartFragmentScreen(
                            "login",
                            state.getEmail()
                    )
            );
        });
    }
}