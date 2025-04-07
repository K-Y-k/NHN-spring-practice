package com.nhnacademy.subjectweek02.controller;

import com.nhnacademy.subjectweek02.domain.Student;
import com.nhnacademy.subjectweek02.domain.StudentRegisterRequest;
import com.nhnacademy.subjectweek02.repository.StudentRepository;
import com.nhnacademy.subjectweek02.repository.StudentRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/register")
    public String studentRegisterForm() {
        return "student/studentRegister";
    }

    @PostMapping("/register")
    public String studentRegister(@ModelAttribute StudentRegisterRequest studentRegisterRequest, Model model) {
        String id = studentRegisterRequest.getId();
        String password = studentRegisterRequest.getPassword();
        String name = studentRegisterRequest.getName();
        String email = studentRegisterRequest.getEmail();
        int score = studentRegisterRequest.getScore();
        String comment = studentRegisterRequest.getComment();

        Student registerStudent = studentRepository.register(id, password, name, email, score, comment);
        model.addAttribute("student", Student.constructPasswordMaskedStudent(registerStudent));

        return "redirect:/student/" + studentRegisterRequest.getId();
    }
}
