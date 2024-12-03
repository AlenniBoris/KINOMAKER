package com.example.kinomaker.data.repository;

import com.example.kinomaker.data.database.ApplicationDatabase;
import com.example.kinomaker.data.mappers.Mapper;
import com.example.kinomaker.domain.model.JobApplication;
import com.example.kinomaker.domain.model.Movie;
import com.example.kinomaker.domain.model.User;
import com.example.kinomaker.domain.repository.UserRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private ApplicationDatabase database;

    @Inject
    public UserRepositoryImpl(
            ApplicationDatabase database
    ){
        this.database = database;
    }

    @Override
    public Single<Boolean> loginUser(
            String email,
            String password
    ) {
       return Single.create(emitter -> {
           database.getDb().collection("users")
                   .document(email)
                   .get()
                   .addOnSuccessListener(documentSnapshot -> {
                       if (documentSnapshot.exists()){
                           String documentedPassword = documentSnapshot.getString("password");
                           if (documentedPassword != null && documentedPassword.equals(password)){
                              emitter.onSuccess(true);
                           }else {
                               emitter.onError(new Exception("Wrong password"));
                           }
                       } else{
                           emitter.onError(new Exception("No such user. Registration is needed"));
                       }
                   })
                   .addOnFailureListener(emitter::onError);
       });
    }

    @Override
    public Single<Boolean> registerUser(
            User user
    ) {
       return Single.create(emitter -> {
           database.getDb().collection("users")
                   .document(user.getEmail())
                   .get()
                   .addOnSuccessListener(snapshot -> {
                       if (snapshot.exists()){
                           emitter.onError(new Exception("Such user exists, sign in"));
                       } else {
                           database.getDb().collection("users")
                                   .document(user.getEmail())
                                   .set(user)
                                   .addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void unused) {
                                           emitter.onSuccess(true);
                                       }
                                   })
                                   .addOnFailureListener(emitter::onError);
                       }
                   });
       });
    }

    @Override
    public Single<User> getUserDataFromServer(
            String email
    ) {
        return Single.create(emitter -> {
            database.getDb().collection("users")
                    .document(email)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot snapshot) {
                            User currentUser = new User(
                                snapshot.getString("firstName"),
                                snapshot.getString("lastName"),
                                snapshot.getString("email"),
                                snapshot.getString("phoneNumber"),
                                snapshot.getString("password"),
                                snapshot.getLong("age") != null ? snapshot.getLong("age").intValue() : 0,
                                snapshot.getString("country"),
                                snapshot.getString("city"),
                                snapshot.getString("gender"),
                                Mapper.toResume(snapshot.get("resume")),
                                Mapper.toVacancyList(snapshot.get("applications"))
                            );

                            emitter.onSuccess(currentUser);
                        }
                    })
                    .addOnFailureListener(emitter::onError);
        });
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
