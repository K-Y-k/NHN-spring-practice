package com.nhnacademy.exam.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhnacademy.exam.employee.domain.Employee;

public interface EmployeeJpaRepository extends JpaRepository<Employee, String> {
}
