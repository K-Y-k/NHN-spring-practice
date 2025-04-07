package com.nhnacademy.subjectweek02.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Student {
    private String id;
    private String password;
    private String name;
    private String email;
    private int score;
    private String comment;

    public static Student create(String id, String password, String name, String email, int score, String comment) {
        return new Student(id, password, name, email, score, comment);
    }

    private static final String MASK = "*****";

    public static Student constructPasswordMaskedStudent(Student student) {
        return Student.create(student.getId(), MASK, student.getName(), student.getEmail(), student.getScore(), student.getComment());
    }
}
