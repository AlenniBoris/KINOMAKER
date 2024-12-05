package com.example.kinomaker.presentation.choosingscreen.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kinomaker.databinding.FragmentChoosingBinding;
import com.example.kinomaker.di.KinomakerApp;
import com.example.kinomaker.navigation.Screen;
import com.example.kinomaker.presentation.applicationscreen.ApplicationFragmentStateHolder;
import com.example.kinomaker.presentation.choosingscreen.ChoosingStateHolder;
import com.example.kinomaker.presentation.choosingscreen.ChoosingViewModel;
import com.example.kinomaker.presentation.workingscreen.WorkingFragment;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public class ChoosingFragment extends Fragment {

    private FragmentChoosingBinding binding;
    private ChoosingViewModel viewModel;

    public ChoosingFragment() {
    }

    public static ChoosingFragment newInstance() {
        return new ChoosingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChoosingBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(ChoosingViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnJobs.setOnClickListener(this::btnAction);
        binding.btnWorkers.setOnClickListener(this::btnAction);

        observeOnScreenState();
    }

    private void observeOnScreenState(){
        viewModel.getObservable()
                .subscribe(new Observer<ChoosingStateHolder>() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(ChoosingStateHolder state) {
                        updateUi(state);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR RXJAVA", "ERROR APPLICATION FRAGMENT: \n" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }

    private void updateUi(ChoosingStateHolder state){

        if (state.isTaskCompleted()){
            KinomakerApp.getRouter().newRootScreen(Screen.ApplicationFragmentScreen());
        }
        if (state.isErrorHappened()){
            Toast.makeText(
                    requireContext(),
                    state.getErrorMessage(),
                    Toast.LENGTH_SHORT
            ).show();
        }

    }

    private void btnAction(View view){
        if (view == binding.btnJobs) {
            viewModel.setUserCompany(0);
        } else {
            viewModel.setUserCompany(1);
        }
    }
}