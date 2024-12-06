package com.example.kinomaker.data.repository;

import static com.example.kinomaker.utils.ExtraFunctions.hasEmptyFields;
import static com.example.kinomaker.utils.ExtraFunctions.stringIsValidatedEmail;

import com.example.kinomaker.data.database.ApplicationDatabase;
import com.example.kinomaker.domain.model.User;
import com.example.kinomaker.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private ApplicationDatabase database;

    @Inject
    public UserRepositoryImpl(
            ApplicationDatabase database
    ) {
        this.database = database;
    }

    @Override
    public Single<Boolean> loginUser(
            String email,
            String password
    ) {
        return Single.create(emitter -> {

            if (!stringIsValidatedEmail(email)) {
                emitter.onError(new Exception("Your entered email is not email-typed"));
                return;
            }

            if (password.isEmpty()) {
                emitter.onError(new Exception("Your entered password is empty"));
                return;
            }

            database.getDb().collection("users")
                    .document(email)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String documentedPassword = documentSnapshot.getString("password");
                            if (documentedPassword != null && documentedPassword.equals(password)) {
                                emitter.onSuccess(true);
                            } else {
                                emitter.onError(new Exception("Wrong password"));
                            }
                        } else {
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

            if (hasEmptyFields(user)) {
                emitter.onError(new Exception("You have to enter all fields"));
                return;
            }

            if (!stringIsValidatedEmail(user.getEmail())) {
                emitter.onError(new Exception("Your entered email is not email-typed"));
                return;
            }

            if (user.getPassword().length() < 6) {
                emitter.onError(new Exception("Password should be at least 6 digits"));
                return;
            }

            if (user.getPassword().isEmpty()) {
                emitter.onError(new Exception("Your entered password is empty"));
                return;
            }

            database.getDb().collection("users")
                    .document(user.getEmail())
                    .get()
                    .addOnSuccessListener(snapshot -> {
                        if (snapshot.exists()) {
                            emitter.onError(new Exception("Such user exists, sign in"));
                        } else {
                            database.getDb().collection("users")
                                    .document(user.getEmail())
                                    .set(user)
                                    .addOnSuccessListener(unused -> emitter.onSuccess(true))
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
                    .addOnSuccessListener(snapshot -> {
                        try {
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
                                    snapshot.getString("company")
                            );

                            emitter.onSuccess(currentUser);
                        } catch (Exception e) {
                            emitter.onError(e);
                        }
                    })
                    .addOnFailureListener(emitter::onError);
        });
    }

    @Override
    public Completable updateUserField(
            String email,
            String field,
            Object value
    ) {
        return Completable.create(emitter -> {
            database.getDb().collection("users")
                    .document(email)
                    .update(field, value)
                    .addOnSuccessListener(unused -> {
                        emitter.onComplete();
                    })
                    .addOnFailureListener(emitter::onError);
        });
    }

}
