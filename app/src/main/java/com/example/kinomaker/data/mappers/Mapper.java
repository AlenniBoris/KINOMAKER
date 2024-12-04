package com.example.kinomaker.data.mappers;

import com.example.kinomaker.domain.model.JobApplication;
import com.example.kinomaker.domain.model.Movie;
import com.example.kinomaker.domain.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mapper {

    public static Resume toResume(Object resumeObject) {
        if (resumeObject instanceof Map) {
            Map<String, Object> resumeMap = (Map<String, Object>) resumeObject;

            ArrayList<Movie> movies = new ArrayList<>();
            if (resumeMap.get("movies") instanceof List) {
                List<Map<String, Object>> moviesList = (List<Map<String, Object>>) resumeMap.get("movies");
                for (Map<String, Object> movieMap : moviesList) {
                    movies.add(new Movie(
                            movieMap.get("year") != null ? ((Number) movieMap.get("year")).intValue() : 0,
                            (String) movieMap.get("title"),
                            (String) movieMap.get("description")
                    ));
                }
            }

            return new Resume(
                    (String) resumeMap.get("profession"),
                    (String) resumeMap.get("phone"),
                    (String) resumeMap.get("email"),
                    (String) resumeMap.get("workExperience"),
                    movies,
                    (String) resumeMap.get("education")
            );
        }
        return null;
    }

    public static ArrayList<JobApplication> toVacancyList(Object vacanciesObject) {
        ArrayList<JobApplication> jobApplications = new ArrayList<>();
        if (vacanciesObject instanceof List) {
            List<Map<String, Object>> vacanciesList = (List<Map<String, Object>>) vacanciesObject;

            for (Map<String, Object> vacancyMap : vacanciesList) {
                try {
                    JobApplication jobApplication = new JobApplication(
                            (String) vacancyMap.get("email"),
                            (String) vacancyMap.get("position"),
                            (String) vacancyMap.get("experience"),
                            vacancyMap.get("salary") != null ? ((Number) vacancyMap.get("salary")).intValue() : 0,
                            vacancyMap.get("formalization") != null && (Boolean) vacancyMap.get("formalization"),
                            (String) vacancyMap.get("description")
                    );
                    jobApplications.add(jobApplication);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return jobApplications;
    }

}
