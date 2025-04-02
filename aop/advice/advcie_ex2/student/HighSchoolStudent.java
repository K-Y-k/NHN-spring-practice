package org.example.student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HighSchoolStudent implements Student {

    private String school;

    @Override
    public String getSchool() {
        return school;
    }
}
