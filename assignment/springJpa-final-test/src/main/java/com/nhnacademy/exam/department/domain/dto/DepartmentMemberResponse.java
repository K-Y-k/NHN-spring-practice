package com.nhnacademy.exam.department.domain.dto;

import com.nhnacademy.exam.department.domain.entity.Department;
import com.nhnacademy.exam.employee.domain.Employee;

import lombok.Value;

@Value
public class DepartmentMemberResponse {
	Department department;
	Employee employee;
}
