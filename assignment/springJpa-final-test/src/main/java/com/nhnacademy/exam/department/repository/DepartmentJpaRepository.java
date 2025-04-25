package com.nhnacademy.exam.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhnacademy.exam.department.domain.entity.Department;

public interface DepartmentJpaRepository extends JpaRepository<Department, String> {
}
