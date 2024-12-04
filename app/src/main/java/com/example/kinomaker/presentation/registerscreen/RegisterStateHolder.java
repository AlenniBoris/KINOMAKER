package com.example.kinomaker.presentation.registerscreen;

public class RegisterStateHolder {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean errorHappened;
    private String errorText;
    private boolean userWasRegistered;

    public RegisterStateHolder() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phoneNumber = "";
        this.password = "";
        this.errorHappened = false;
        this.errorText = "";
        this.userWasRegistered = false;
    }

    public boolean isUserWasRegistered() {
        return userWasRegistered;
    }

    public void setUserWasRegistered(boolean userWasRegistered) {
        this.userWasRegistered = userWasRegistered;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public Boolean getErrorHappened() {
        return errorHappened;
    }

    public void setErrorHappened(Boolean errorHappened) {
        this.errorHappened = errorHappened;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
