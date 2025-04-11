package com.nhnacademy.finalsubjectweek02.customer.repository.impl;

import com.nhnacademy.finalsubjectweek02.customer.domain.Customer;
import com.nhnacademy.finalsubjectweek02.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<String, Customer> customerMap = new HashMap<>();

    public CustomerRepositoryImpl() {
        log.info("CustomerRepositoryImpl and sample Data init");
        customerMap.put("tom", new Customer("tom", "123", "tom"));
        customerMap.put("jake", new Customer("jake", "123", "jake"));
        customerMap.put("celine", new Customer("celine", "123", "celine"));
        customerMap.put("asd", new Customer("asd", "asd", "asd"));
    }


//    @Override
//    public Customer saveCustomer(Customer customer) {
//        if (existsCustomer(customer.getId())) {
//            throw new CustomerAlreadyExistsException("Customer with id " + customer.getId() + " already exists");
//        }
//
//        return customerMap.put(customer.getId(), customer);
//    }

    @Override
    public Customer findById(String id) {
        Customer findCustomer = customerMap.get(id);
        if (Objects.isNull(findCustomer)) {
            return null;
        }

        return customerMap.get(id);
    }

    @Override
    public boolean existsCustomer(String id) {
        return customerMap.containsKey(id);
    }
}
