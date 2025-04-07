package com.nhnacademy.subjectweek02.repository;

import com.nhnacademy.subjectweek02.domain.Student;
import com.nhnacademy.subjectweek02.exception.StudentAlreadyExistsException;
import com.nhnacademy.subjectweek02.exception.StudentNotExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<String, Student> studentMap = new HashMap<>();

    public StudentRepositoryImpl() {
        studentMap.put("tom", Student.create("tom", "1234", "tom", "asd@asd.com", 70, "ddddd"));
        studentMap.put("jake", Student.create("jake", "1234", "jake", "asd@asd.com", 30, "ddddd"));
        studentMap.put("hay", Student.create("hay", "1234", "hay", "asd@asd.com", 50, "ddddd"));
        studentMap.put("celine", Student.create("celine", "1234", "celine", "asd@asd.com", 80, "ddddd"));
    }

    @Override
    public Student register(String id, String password, String name, String email, int score, String comment) {
        if (exits(id)) {
            throw new StudentAlreadyExistsException("Student with id " + id + " already exists");
        }

        Student registerStudent = Student.create(id, password, name, email, score, comment);
        studentMap.put(registerStudent.getId(), registerStudent);

        log.info("Student {} has been registered", registerStudent.getId());
        for (Student value : studentMap.values()) {
            log.info("{}", value.toString());
        }

        return registerStudent;
    }

    @Override
    public Student update(String id, String password, String name, String email, int score, String comment) {
        Student updateStudent = Student.create(id, password, name, email, score, comment);
        studentMap.put(updateStudent.getId(), updateStudent);

        log.info("Student {} has been updated", updateStudent.getId());
        for (Student value : studentMap.values()) {
            log.info("{}", value.toString());
        }
        return studentMap.put(updateStudent.getId(), updateStudent);
    }

    @Override
    public Student getStudent(String id) {
        if (!exits(id)) {
            throw new StudentNotExistsException("Student with id " + id + " does not exist");
        }

        return studentMap.get(id);
    }

    @Override
    public boolean exits(String id) {
        return studentMap.containsKey(id);
    }

    @Override
    public boolean match(String id, String password) {
        return Optional.ofNullable(getStudent(id))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}
