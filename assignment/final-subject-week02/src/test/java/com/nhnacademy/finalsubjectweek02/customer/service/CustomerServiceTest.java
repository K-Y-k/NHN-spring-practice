package com.nhnacademy.finalsubjectweek02.customer.service;

import com.nhnacademy.finalsubjectweek02.customer.domain.Customer;
import com.nhnacademy.finalsubjectweek02.customer.exception.CustomerNotFoundException;
import com.nhnacademy.finalsubjectweek02.customer.repository.CustomerRepository;
import com.nhnacademy.finalsubjectweek02.customer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private final String CUSTOMER_ID = "tom";
    private final String CUSTOMER_PASSWORD = "123";
    private Customer sampleCustomer = new Customer(CUSTOMER_ID, CUSTOMER_PASSWORD, "tom");


    @Test
    @DisplayName("고객 조회 테스트")
    void getCustomer() {
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(sampleCustomer);

        Customer result = customerService.getCustomer(CUSTOMER_ID);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("tom");
        assertThat(result.getName()).isEqualTo("tom");
        verify(customerRepository).findById("tom");
    }

    @Test
    @DisplayName("고객 조회 테스트 - 존재하지 않는 고객")
    void getCustomer_notFound() {
        when(customerRepository.findById("x")).thenReturn(null);

        assertThatThrownBy(() -> customerService.getCustomer("x"))
                .isInstanceOf(CustomerNotFoundException.class);

        verify(customerRepository).findById("x");
    }

    @Test
    @DisplayName("ID와 비밀번호 일치 테스트")
    void match() {
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(sampleCustomer);

        boolean result = customerService.match(sampleCustomer.getId(), sampleCustomer.getPassword());

        assertThat(result).isTrue();
        verify(customerRepository).findById(CUSTOMER_ID);
    }

    @Test
    @DisplayName("ID와 비밀번호 일치 여부 확인 테스트 - 실패(ID, 비밀번호 불일치)")
    void match_Failure_Invalid() {
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(sampleCustomer);

        boolean result1 = customerService.match(sampleCustomer.getId(), "x");
        boolean result2 = customerService.match("x", sampleCustomer.getPassword());
        boolean result3 = customerService.match("x", "x");

        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        verify(customerRepository).findById(CUSTOMER_ID);
    }

    @Test
    @DisplayName("고객 존재 여부 확인 테스트")
    void existsCustomer() {
        when(customerRepository.existsCustomer(sampleCustomer.getId())).thenReturn(true);
        when(customerRepository.existsCustomer("x")).thenReturn(false);

        assertThat(customerService.existsCustomer(sampleCustomer.getId())).isTrue();
        assertThat(customerService.existsCustomer("x")).isFalse();
        verify(customerRepository).existsCustomer("x");
    }
}
