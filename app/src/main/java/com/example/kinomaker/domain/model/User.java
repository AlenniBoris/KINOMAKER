package com.example.kinomaker.domain.model;

import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private int age;
    private String country;
    private String city;
    private String gender;
    private Resume resume;
    private ArrayList<JobApplication> applications;

    public User() {}

    public User(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String password,
            int age,
            String country,
            String city,
            String gender,
            Resume resume,
            ArrayList<JobApplication> applications
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.age = age;
        this.country = country;
        this.city = city;
        this.gender = gender;
        this.resume = resume;
        this.applications = applications;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public ArrayList<JobApplication> getApplications() {
        return applications;
    }

    public void setApplications(ArrayList<JobApplication> applications) {
        this.applications = applications;
    }
}