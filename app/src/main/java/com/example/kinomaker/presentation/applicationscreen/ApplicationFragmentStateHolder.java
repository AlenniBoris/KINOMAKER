package com.example.kinomaker.presentation.applicationscreen;

import com.example.kinomaker.domain.model.User;

public class ApplicationFragmentStateHolder {

    private String currentUserEmailFromFirebaseAuth;
    private User currentUser;
    private Boolean errorHappened;
    private String errorText;

    public ApplicationFragmentStateHolder() {
        this.currentUserEmailFromFirebaseAuth = "";
        this.currentUser = null;
        this.errorHappened = false;
        this.errorText = "";
    }

    public String getCurrentUserEmailFromFirebaseAuth() {
        return currentUserEmailFromFirebaseAuth;
    }

    public void setCurrentUserEmailFromFirebaseAuth(String currentUserEmailFromFirebaseAuth) {
        this.currentUserEmailFromFirebaseAuth = currentUserEmailFromFirebaseAuth;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Boolean getErrorHappened() {
        return errorHappened;
    }

    public void setErrorHappened(Boolean errorHappened) {
        this.errorHappened = errorHappened;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
