package com.nhnacademy.finalsubjectweek02.customer.controller;

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

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InquiryService inquiryService;

    @MockitoBean
    private LoginInterceptor loginInterceptor;

    private final String CUSTOMER_ID = "tom";


    @BeforeEach
    void setUp() throws Exception {
        when(loginInterceptor.preHandle(any(), any(), any())).thenReturn(true);
    }


    @Test
    @DisplayName("고객 메인 폼 이동 테스트")
    void customerMainForm() throws Exception {
        List<Inquiry> inquiryList = List.of(
                new Inquiry(CUSTOMER_ID, "불만 접수", "제목1", "내용1", "tom"),
                new Inquiry(CUSTOMER_ID, "제안", "제목2", "내용2", "tom")
        );


        when(inquiryService.getInquiryListByCustomerId(CUSTOMER_ID)).thenReturn(inquiryList);

        mockMvc.perform(get("/cs/" + CUSTOMER_ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("inquiryList", inquiryList))
                .andExpect(model().attribute("customerId", CUSTOMER_ID))
                .andExpect(view().name("customer/customerMain"));
    }

    @Test
    @DisplayName("고객 메인 폼 테스트 - 분류 검색")
    void customerMain_withClassification() throws Exception {
        List<Inquiry> inquiryListByClassification = List.of(
                new Inquiry(CUSTOMER_ID, "불만 접수", "제목1", "내용1", "tom"),
                new Inquiry(CUSTOMER_ID, "불만 접수", "제목2", "내용2", "tom")
        );

        when(inquiryService.getInquiryListByClassification(CUSTOMER_ID, "불만 접수")).thenReturn(inquiryListByClassification);

        mockMvc.perform(get("/cs/" + CUSTOMER_ID + "/search")
                        .param("classification", "불만 접수"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerMain"))
                .andExpect(model().attribute("inquiryList", inquiryListByClassification))
                .andExpect(model().attribute("classification", "불만 접수"));
    }
}
