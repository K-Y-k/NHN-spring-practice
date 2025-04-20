package com.nhnacademy.finalsubjectweek03.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;


    /// Admin 페이지 인가 테스트
    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    @DisplayName("Admin 페이지 - ADMIN 통과 케이스")
    public void adminPageInADMIN() throws Exception {
        mockMvc.perform(get("/main/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"MEMBER"})
    @DisplayName("Admin 페이지 - GOOGLE 403 에러 케이스")
    public void adminPageInMEMBER() throws Exception {
        mockMvc.perform(get("/main/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_GOOGLE"})
    @DisplayName("Admin 페이지 - MEMBER 403 에러 케이스")
    public void adminPageInGOOGLE() throws Exception {
        mockMvc.perform(get("/main/admin"))
                .andExpect(status().isForbidden());
    }


    /// Member 페이지 인가 테스트
    @Test
    @WithMockUser(username = "user", roles = {"MEMBER"})
    @DisplayName("Member 페이지 - MEMBER 통과 케이스")
    public void memberPageInMEMBER() throws Exception {
        mockMvc.perform(get("/main/member"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_ADMIN"})
    @DisplayName("Member 페이지 - ADMIN 403 에러 케이스")
    public void memberPageInADMIN() throws Exception {
        mockMvc.perform(get("/main/member"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"GOOGLE"})
    @DisplayName("Member 페이지 - GOOGLE 403 에러 케이스")
    public void memberPageInGOOGLE() throws Exception {
        mockMvc.perform(get("/main/member"))
                .andExpect(status().isForbidden());
    }


    /// GOOGLE 페이지 인가 테스트
    @Test
    @WithMockUser(username = "user", roles = {"GOOGLE"})
    @DisplayName("Admin 페이지 - GOOGLE 통과 케이스")
    public void googlePageInGOOGLE() throws Exception {
        mockMvc.perform(get("/main/google"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    @DisplayName("Admin 페이지 - ADMIN 403 에러 케이스")
    public void googlePageInADMIN() throws Exception {
        mockMvc.perform(get("/main/google"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"MEMBER"})
    @DisplayName("Admin 페이지 - MEMBER 403 에러 케이스")
    public void googlePageInMEMBER() throws Exception {
        mockMvc.perform(get("/main/google"))
                .andExpect(status().isForbidden());
    }

}
