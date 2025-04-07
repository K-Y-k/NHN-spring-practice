package com.nhnacademy.subjectweek02.repository;

import com.nhnacademy.subjectweek02.domain.Student;

public interface StudentRepository {
    Student register(String id, String password, String name, String email, int score, String comment);
    Student update(String id, String password, String name, String email, int score, String comment);
    Student getStudent(String id);

    boolean exits(String id);
    boolean match(String id, String password);
}
