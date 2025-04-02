package org.example.student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniversityStudent implements Student {

    private String school;

    @Override
    public String getSchool() {
        return school;
    }
}
