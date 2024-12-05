package com.example.kinomaker.presentation.choosingscreen;

import com.example.kinomaker.domain.model.User;

public class ChoosingStateHolder {

    private boolean isChosen;
    private boolean errorHappened;
    private String errorMessage;
    private User user;
    private boolean isTaskCompleted;

    public ChoosingStateHolder() {
        this.isChosen = false;
        this.errorHappened = false;
        this.errorMessage = "";
        this.user = null;
        this.isTaskCompleted = false;
    }

    public boolean isTaskCompleted() {
        return isTaskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        isTaskCompleted = taskCompleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public boolean isErrorHappened() {
        return errorHappened;
    }

    public void setErrorHappened(boolean errorHappened) {
        this.errorHappened = errorHappened;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
