package com.nhnacademy.subjectweek02.controller;

import com.nhnacademy.subjectweek02.domain.Student;
import com.nhnacademy.subjectweek02.interceptor.LoginInterceptor;
import com.nhnacademy.subjectweek02.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentRegisterController.class)
public class StudentRegisterTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentRepository studentRepository;

    @MockitoBean
    private LoginInterceptor loginInterceptor;

    private final String STUDENT_ID = "studentId1";


    @BeforeEach
    void setUp() throws Exception {
        when(loginInterceptor.preHandle(any(), any(), any())).thenReturn(true);
    }


    @Test
    @DisplayName("학생 등록 폼 이동 테스트")
    void registerForm() throws Exception {
        mockMvc.perform(get("/student/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentRegister"));
    }

    @Test
    @DisplayName("학생 등록 테스트")
    void register() throws Exception {
        when(studentRepository.register(any(),any(), any(), any(), anyInt(), any()))
                .thenReturn(Student.create(STUDENT_ID, "password1", "name1", "email1", 0, "comment1"));

        mockMvc.perform(post("/student/register")
                        .param("id", STUDENT_ID)
                        .param("password", "password1")
                        .param("name", "name1")
                        .param("email", "email1")
                        .param("score", "0")
                        .param("comment", "comment1"))
                .andExpect(redirectedUrl("/student/" + STUDENT_ID));

    }
}
