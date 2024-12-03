package com.example.kinomaker.data.repository;

import com.example.kinomaker.data.database.ApplicationDatabase;
import com.example.kinomaker.domain.model.JobApplication;
import com.example.kinomaker.domain.model.Movie;
import com.example.kinomaker.domain.model.User;
import com.example.kinomaker.domain.repository.ApplicationRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private ApplicationDatabase database;

    @Inject
    public ApplicationRepositoryImpl(
            ApplicationDatabase database
    ){
        this.database = database;
    }

    @Override
    public void loginUser(
            String email,
            String password,
            OnSuccessListener<DocumentSnapshot> onSuccessListener,
            OnFailureListener onFailureListener
    ) {
       database.getDb().collection("users")
               .document(email)
               .get()
               .addOnSuccessListener(documentSnapshot -> {
                   if (documentSnapshot.exists()){
                       String documentedPassword = documentSnapshot.getString("password");
                       if (documentedPassword != null && documentedPassword.equals(password)){
                           onSuccessListener.onSuccess(documentSnapshot);
                       }else {
                           onFailureListener.onFailure(new Exception("Wrong password"));
                       }
                   } else{
                       onFailureListener.onFailure(new Exception("No such user. Registration is needed"));
                   }
               })
               .addOnFailureListener(onFailureListener);
    }

    @Override
    public void registerUser(
            User user,
            OnSuccessListener<Void> onSuccessListener,
            OnFailureListener onFailureListener
    ) {
       database.getDb().collection("users")
               .document(user.getEmail())
               .set(user)
               .addOnSuccessListener(onSuccessListener)
               .addOnFailureListener(onFailureListener);
    }

    @Override
    public void updateUserField(
            String email,
            String field,
            Object value,
            OnSuccessListener<Void> onSuccessListener,
            OnFailureListener onFailureListener
    ) {
        database.getDb().collection("users")
                .document(email)
                .update(field, value)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    @Override
    public void addNewMovieToResume(
            String email,
            Movie movie,
            OnSuccessListener<Void> onSuccessListener,
            OnFailureListener onFailureListener
    ) {
        database.getDb().collection("users")
                .document(email)
                .update("resume.movies", FieldValue.arrayUnion(movie))
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    @Override
    public void addNewJobApplication(
            String email,
            JobApplication jobApplication,
            OnSuccessListener<Void> onSuccessListener,
            OnFailureListener onFailureListener
    ) {
        database.getDb().collection("users")
                .document(email)
                .update("applications", FieldValue.arrayUnion(jobApplication))
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
}
