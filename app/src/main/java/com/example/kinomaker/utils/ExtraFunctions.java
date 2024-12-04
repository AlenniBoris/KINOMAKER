package com.example.kinomaker.utils;

import com.example.kinomaker.domain.model.User;

public class ExtraFunctions {

    public static boolean stringIsValidatedEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean hasEmptyFields(User user) {
        return user.getFirstName().isBlank() ||
                user.getLastName().isBlank() ||
                user.getEmail().isBlank() ||
                user.getPhoneNumber().isBlank() ||
                user.getPassword().isBlank();
    }

}
