package com.example.kinomaker.domain.repository;

import com.example.kinomaker.domain.model.JobApplication;
import com.example.kinomaker.domain.model.Movie;
import com.example.kinomaker.domain.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

public interface ApplicationRepository {

    public void loginUser(
            String email,
            String password,
            OnSuccessListener<DocumentSnapshot> onSuccessListener,
            OnFailureListener onFailureListener
            );

    public void registerUser(
            User user,
            OnSuccessListener<Void> onSuccessListener,
            OnFailureListener onFailureListener
    );

    public void getUserDataFromServer(
            String email,
            OnSuccessListener<DocumentSnapshot> onSuccess,
            OnFailureListener onFailure
    );

    public void updateUserField(
            String email,
            String field,
            Object value,
            OnSuccessListener<Void> onSuccessListener,
            OnFailureListener onFailureListener
    );

    public void addNewMovieToResume(
            String email,
            Movie movie,
            OnSuccessListener<Void> onSuccessListener,
            OnFailureListener onFailureListener
    );

    public void addNewJobApplication(
            String email,
            JobApplication jobApplication,
            OnSuccessListener<Void> onSuccessListener,
            OnFailureListener onFailureListener
    );

}
