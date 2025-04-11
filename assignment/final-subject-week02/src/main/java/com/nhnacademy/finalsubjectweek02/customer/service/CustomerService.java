package com.nhnacademy.finalsubjectweek02.customer.service;

import com.nhnacademy.finalsubjectweek02.customer.domain.Customer;

public interface CustomerService {
    Customer getCustomer(String id);
    boolean match(String id, String password);
    boolean existsCustomer(String id);
}
