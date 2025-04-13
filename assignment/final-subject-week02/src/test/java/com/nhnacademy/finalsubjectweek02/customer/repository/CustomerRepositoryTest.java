package com.nhnacademy.finalsubjectweek02.customer.repository;

import com.nhnacademy.finalsubjectweek02.customer.domain.Customer;
import com.nhnacademy.finalsubjectweek02.customer.repository.impl.CustomerRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomerRepositoryTest {

    private final CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();

    private Customer sampleCustomer = new Customer("tom", "123", "tom");


    @Test
    @DisplayName("고객 찾기 테스트")
    void findById() {
        Customer customer = customerRepository.findById(sampleCustomer.getId());

        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isEqualTo("tom");
        assertThat(customer.getPassword()).isEqualTo("123");
        assertThat(customer.getName()).isEqualTo("tom");
    }

    @Test
    @DisplayName("고객 찾기 테스트 - 실패(존재하지 않는 고객)")
    void findById_NotExist() {
        Customer customer = customerRepository.findById("x");

        assertThat(customer).isNull();
    }

    @Test
    @DisplayName("고객 존재 여부 확인 테스트")
    void existsCustomer_existingCustomer_returnsTrue() {
        boolean exists = customerRepository.existsCustomer(sampleCustomer.getId());

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("고객 존재 여부 확인 테스트 - 실패(존재하지 않는 고객)")
    void existsCustomer_nonExistingCustomer_returnsFalse() {
        boolean exists = customerRepository.existsCustomer("x");

        assertThat(exists).isFalse();
    }
}
