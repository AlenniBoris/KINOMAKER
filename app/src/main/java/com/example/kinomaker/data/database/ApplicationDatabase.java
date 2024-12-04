package com.example.kinomaker.data.database;

import com.google.firebase.firestore.FirebaseFirestore;

public class ApplicationDatabase {

    private FirebaseFirestore db;

    public ApplicationDatabase() {
        db = FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getDb() {
        return db;
    }
}
