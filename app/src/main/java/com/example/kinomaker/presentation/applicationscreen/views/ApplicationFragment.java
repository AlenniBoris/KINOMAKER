package com.example.kinomaker.presentation.applicationscreen.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.kinomaker.databinding.FragmentApplicationBinding;
import com.example.kinomaker.presentation.applicationscreen.ApplicationFragmentStateHolder;
import com.example.kinomaker.presentation.applicationscreen.ApplicationFragmentViewModel;
import com.example.kinomaker.presentation.choosingscreen.views.ChoosingFragment;
import com.example.kinomaker.presentation.workingscreen.WorkingFragment;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

@AndroidEntryPoint
public class ApplicationFragment extends Fragment {

    private FragmentApplicationBinding binding;
    private ApplicationFragmentViewModel viewModel;

    public ApplicationFragment() {
    }

    public static ApplicationFragment newInstance() {
        return new ApplicationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ApplicationFragmentViewModel.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentApplicationBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        observeOnScreenState();
    }

    private void observeOnScreenState(){
        viewModel.getStateObservable()
                .subscribe(new Observer<ApplicationFragmentStateHolder>() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(ApplicationFragmentStateHolder state) {
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

    private void updateUi(ApplicationFragmentStateHolder state){

        if (!state.getCurrentUser().isCompany().isEmpty()){
            getParentFragmentManager().beginTransaction()
                    .replace(
                            binding.flApplicationContainer.getId(),
                            new WorkingFragment(),
                            "Working fragment"
                    ).commit();
        } else {
            getParentFragmentManager().beginTransaction()
                    .replace(
                            binding.flApplicationContainer.getId(),
                            new ChoosingFragment(),
                            "Working fragment"
                    ).commit();
        }

    }
}