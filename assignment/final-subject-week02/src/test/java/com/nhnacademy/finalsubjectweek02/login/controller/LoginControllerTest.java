package com.nhnacademy.finalsubjectweek02.login.controller;

import com.nhnacademy.finalsubjectweek02.admin.domain.Admin;
import com.nhnacademy.finalsubjectweek02.admin.service.AdminService;
import com.nhnacademy.finalsubjectweek02.customer.domain.Customer;
import com.nhnacademy.finalsubjectweek02.customer.service.CustomerService;
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

@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AdminService adminService;

    @MockitoBean
    private CustomerService customerService;

    private final String SESSION_ID = "SESSION";
    private final String USER_ID = "userId1";


    @Test
    @DisplayName("로그인 폼 이동 테스트")
    void loginForm() throws Exception {
        mockMvc.perform(get("/cs/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"));
    }

    @Test
    @DisplayName("로그인 폼 이동 테스트 - 현재 고객 계정 세션이 있는 경우")
    void loginForm_Login_Success_Customer() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SESSION", SESSION_ID);
        session.setAttribute("userId", USER_ID);

        when(customerService.getCustomer(USER_ID)).thenReturn(new Customer(USER_ID, "password1", "name1"));

        mockMvc.perform(get("/cs/login")
                        .session(session)
                        .cookie(new Cookie("SESSION", SESSION_ID)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/userId1"));
    }

    @Test
    @DisplayName("로그인 폼 이동 테스트 - 현재 관리자 계정 세션이 있는 경우")
    void loginForm_Login_Success_Admin() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SESSION", SESSION_ID);
        session.setAttribute("userId", USER_ID);

        when(adminService.existsAdmin(USER_ID)).thenReturn(true);
        when(adminService.getAdmin(USER_ID)).thenReturn(new Admin(USER_ID, "password1", "name1"));

        mockMvc.perform(get("/cs/login")
                        .session(session)
                        .cookie(new Cookie("SESSION", SESSION_ID)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin/userId1"));
    }

    @Test
    @DisplayName("로그인 테스트 - 성공(관리자계정 로그인")
    void login_Admin_Success() throws Exception {
        when(adminService.match(USER_ID, "password1")).thenReturn(true);
        when(adminService.existsAdmin(USER_ID)).thenReturn(true);

        mockMvc.perform(post("/cs/login")
                        .param("id", USER_ID)
                        .param("password", "password1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/admin/" + USER_ID));
    }

    @Test
    @DisplayName("로그인 테스트 - 성공(고객계정 로그인)")
    void login_Customer_Success() throws Exception {
        when(customerService.match(USER_ID, "password1")).thenReturn(true);
        when(customerService.getCustomer(USER_ID)).thenReturn(new Customer(USER_ID, "password1", "name1"));

        mockMvc.perform(post("/cs/login")
                        .param("id", USER_ID)
                        .param("password", "password1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/userId1"));
    }

    @Test
    @DisplayName("로그인 테스트 - 실패(잘못된 입력값)")
    void login_Failure_NotMatchInput() throws Exception {
        mockMvc.perform(post("/cs/login")
                        .param("id", USER_ID)
                        .param("password", "password1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/login"));
    }

    @Test
    @DisplayName("로그인 테스트 - 실패(id 또는 password 빈값)")
    void login_Failure_EmptyInput() throws Exception {
        mockMvc.perform(post("/cs/login")
                        .param("id", "")
                        .param("password", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/login"));
    }

    @Test
    @DisplayName("로그아웃 테스트 - 세션이 있는 경우")
    void logout_WithSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("SESSION", SESSION_ID);
        session.setAttribute("userId", USER_ID);

        mockMvc.perform(post("/cs/logout")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/login"));
    }

    @Test
    @DisplayName("로그아웃 테스트 - 세션이 없는 경우")
    void logout() throws Exception {
        mockMvc.perform(post("/cs/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cs/login"));
    }
}
