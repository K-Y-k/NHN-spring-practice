package com.nhnacademy.finalsubjectweek02.customer.service.impl;

import com.nhnacademy.finalsubjectweek02.customer.domain.Customer;
import com.nhnacademy.finalsubjectweek02.customer.exception.CustomerNotFoundException;
import com.nhnacademy.finalsubjectweek02.customer.repository.CustomerRepository;
import com.nhnacademy.finalsubjectweek02.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(String id) {
        Customer findCustomer = customerRepository.findById(id);

        if (Objects.isNull(findCustomer)) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }

        return findCustomer;
    }

    @Override
    public boolean match(String id, String password) {
        return Optional.ofNullable(customerRepository.findById(id))
                .map(customer -> customer.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public boolean existsCustomer(String id) {
        return customerRepository.existsCustomer(id);
    }
}
