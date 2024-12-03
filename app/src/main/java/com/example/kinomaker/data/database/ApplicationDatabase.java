package com.example.kinomaker.data.database;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class ApplicationDatabase {

    private FirebaseFirestore db;

    public ApplicationDatabase(){
        db = FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getDb() {
        return db;
    }
}
