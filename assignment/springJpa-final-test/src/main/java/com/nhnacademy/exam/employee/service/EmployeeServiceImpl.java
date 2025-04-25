package com.nhnacademy.exam.employee.service;

import org.springframework.stereotype.Service;

import com.nhnacademy.exam.employee.domain.Employee;
import com.nhnacademy.exam.employee.repository.EmployeeJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl {

	private final EmployeeJpaRepository employeeRepository;

	public void save(Employee employee){
		employeeRepository.save(employee);
	}

	public boolean exist(String id){
		return employeeRepository.findById(id).isPresent();
	}

}
