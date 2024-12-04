package com.example.kinomaker.domain.model;

public class JobApplication {

    private String email;
    private String position;
    private String experience;
    private int salary;
    private boolean formalization;
    private String description;

    public JobApplication() {
    }

    public JobApplication(
            String email,
            String position,
            String experience,
            int salary,
            boolean formalization,
            String description
    ) {
        this.email = email;
        this.position = position;
        this.experience = experience;
        this.salary = salary;
        this.formalization = formalization;
        this.description = description;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isFormalization() {
        return formalization;
    }

    public void setFormalization(boolean formalization) {
        this.formalization = formalization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
