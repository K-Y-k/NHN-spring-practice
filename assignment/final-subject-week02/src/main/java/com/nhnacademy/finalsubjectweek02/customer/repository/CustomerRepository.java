package com.nhnacademy.finalsubjectweek02.customer.repository;

import com.nhnacademy.finalsubjectweek02.customer.domain.Customer;

public interface CustomerRepository {
    Customer findById(String id);
    boolean existsCustomer(String id);
}
