package com.nhnacademy.subjectweek02.controller;

import com.nhnacademy.subjectweek02.domain.Student;
import com.nhnacademy.subjectweek02.repository.StudentRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentLoginController.class)
public class StudentLoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentRepository studentRepository;

    private final String SESSION_ID = "SESSION";
    private final String STUDENT_ID = "studentId1";


    @Test
    @DisplayName("로그인 폼 이동 테스트")
    void loginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"));
    }

    @Test
    @DisplayName("로그인 폼 이동 테스트 - 현재 로그인 세션이 있는 경우")
    void loginForm_Login_Success() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SESSION", SESSION_ID);
        session.setAttribute("studentId", STUDENT_ID);

        when(studentRepository.getStudent(STUDENT_ID)).thenReturn(Student.create(STUDENT_ID, "password1", "name1", "email1", 0, "comment1"));

        mockMvc.perform(get("/login")
                        .session(session)
                        .cookie(new Cookie("SESSION", SESSION_ID)))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentView"));
    }

    @Test
    @DisplayName("로그인 테스트 - 성공")
    void login_Success() throws Exception {
        when(studentRepository.match(STUDENT_ID, "password1")).thenReturn(true);
        when(studentRepository.getStudent(STUDENT_ID)).thenReturn(Student.create(STUDENT_ID, "password1", "name1", "email1", 0, "comment1"));

        mockMvc.perform(post("/login")
                        .param("id", STUDENT_ID)
                        .param("password", "password1"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentView"));
    }

    @Test
    @DisplayName("로그인 테스트 - 실패")
    void login_Failure() throws Exception {
        mockMvc.perform(post("/login")
                        .param("id", STUDENT_ID)
                        .param("password", "password1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @DisplayName("로그아웃 테스트")
    void logoutTest() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
