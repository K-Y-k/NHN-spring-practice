package com.nhnacademy.finalsubjectweek02.admin.controller;

import com.nhnacademy.finalsubjectweek02.common.interceptor.LoginInterceptor;
import com.nhnacademy.finalsubjectweek02.inquiry.domain.Inquiry;
import com.nhnacademy.finalsubjectweek02.inquiry.service.InquiryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InquiryService inquiryService;

    @MockitoBean
    private LoginInterceptor loginInterceptor;

    private final String ADMIN_ID = "admin1";


    @BeforeEach
    void setUp() throws Exception {
        when(loginInterceptor.preHandle(any(), any(), any())).thenReturn(true);
    }


    @Test
    @DisplayName("관리자 메인 폼 이동 테스트")
    void adminMainForm() throws Exception {
        List<Inquiry> inquiryList = List.of(
                new Inquiry("customerId1", "불만 접수", "제목1", "내용1", "customer1"),
                new Inquiry("customerId2", "제안", "제목2", "내용2", "customer2")
        );

        when(inquiryService.getInquiryListByNotAnswered()).thenReturn(inquiryList);

        mockMvc.perform(get("/cs/admin/" + ADMIN_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("inquiryList"))
                .andExpect(model().attribute("adminId", ADMIN_ID))
                .andExpect(view().name("admin/adminMain"));
    }

    @Test
    @DisplayName("관리자 메인 폼 테스트 - 분류 선택")
    void adminMainForm_filteredByClassification() throws Exception {
        List<Inquiry> inquiryListByClassification = List.of(
                new Inquiry("3", "불만 접수", "회원가입이 안돼요", "회원가입이 안돼요", "customer1")
        );

        when(inquiryService.getInquiryListByNotAnsweredAndClassification("불만 접수")).thenReturn(inquiryListByClassification);

        mockMvc.perform(get("/cs/admin/" + ADMIN_ID + "/search")
                        .param("classification", "불만 접수"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("inquiryList"))
                .andExpect(model().attribute("adminId", ADMIN_ID))
                .andExpect(model().attribute("classification", "불만 접수"))
                .andExpect(view().name("admin/adminMain"));
    }
}
