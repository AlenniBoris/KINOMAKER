package com.example.kinomaker.domain.model;

public class Movie {

    private int year;
    private String title;
    private String description;

    public Movie(
            int year,
            String title,
            String description
    ) {
        this.year = year;
        this.title = title;
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
