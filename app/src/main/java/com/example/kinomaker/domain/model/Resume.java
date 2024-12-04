package com.example.kinomaker.domain.model;

import java.util.ArrayList;

public class Resume {

    private String email;
    private String profession;
    private String phone;
    private String workExperience;
    private ArrayList<Movie> movies;
    private String education;

    public Resume() {
    }

    public Resume(
            String profession,
            String phone,
            String email,
            String workExperience,
            ArrayList<Movie> movies,
            String education
    ) {
        this.profession = profession;
        this.phone = phone;
        this.email = email;
        this.workExperience = workExperience;
        this.movies = movies;
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
