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

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
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
        when(studentRepository.getStudent(STUDENT_ID)).thenReturn(Student.create(STUDENT_ID, "password1", "name1", "email1", 0, "comment1"));
    }


    @Test
    @DisplayName("학생 뷰 이동 테스트")
    void viewForm() throws Exception {
        mockMvc.perform(get("/student/{studentId}", STUDENT_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("student"))
                .andExpect(view().name("student/studentView"));
    }

    @Test
    @DisplayName("학생 뷰 이동 테스트 - hideScore 파라미터 케이스")
    void viewFormHideScore() throws Exception {
        mockMvc.perform(get("/student/{studentId}", STUDENT_ID)
                        .param("hideScore", "yes"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("hideScore", "yes"))
                .andExpect(model().attributeExists("student"))
                .andExpect(view().name("student/studentView"));
    }

    @Test
    @DisplayName("수정 폼 이동 테스트")
    void modifyForm() throws Exception {
        mockMvc.perform(get("/student/{studentId}/modify", STUDENT_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("student"))
                .andExpect(view().name("student/studentModify"));
    }

    @Test
    @DisplayName("수정 테스트")
    void modify() throws Exception {
        when(studentRepository.update(any(), any(), any(), any(), anyInt(), any()))
                .thenReturn(Student.create(STUDENT_ID, "updatePassword1", "updateName1", "updateEmail1", 0, "updateComment1"));

        mockMvc.perform(post("/student/{studentId}/modify", STUDENT_ID)
                        .param("id", STUDENT_ID)
                        .param("password", "password1")
                        .param("name", "name1")
                        .param("email", "email1")
                        .param("score", "0")
                        .param("comment", "comment1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/" + STUDENT_ID));
    }
}
