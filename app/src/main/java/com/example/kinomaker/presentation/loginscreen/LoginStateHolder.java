package com.example.kinomaker.presentation.loginscreen;

public class LoginStateHolder {

    private boolean errorHappened;
    private String errorText;
    private boolean userIsAdded;
    private String enteredEmail;
    private String enteredPassword;

    public LoginStateHolder() {
        this.errorHappened = false;
        this.errorText = "";
        this.userIsAdded = false;
        enteredEmail = "";
        enteredPassword = "";
    }

    public String getEnteredEmail() {
        return enteredEmail;
    }

    public void setEnteredEmail(String enteredEmail) {
        this.enteredEmail = enteredEmail;
    }

    public String getEnteredPassword() {
        return enteredPassword;
    }

    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }

    public boolean isErrorHappened() {
        return errorHappened;
    }

    public void setErrorHappened(boolean errorHappened) {
        this.errorHappened = errorHappened;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public boolean isUserIsAdded() {
        return userIsAdded;
    }

    public void setUserIsAdded(boolean userIsAdded) {
        this.userIsAdded = userIsAdded;
    }
}
