package com.example.kinomaker.presentation.workingscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.kinomaker.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WorkingFragment extends Fragment {

    public static WorkingFragment newInstance() {
        return new WorkingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_working, container, false);
    }
}