package com.nhnacademy.exam.department.domain.entity;

import com.nhnacademy.exam.employee.domain.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentMemberId;

	@ManyToOne(optional = false)
	private Department department;

	@ManyToOne(optional = false)
	private Employee employee;

	public DepartmentMember(Department department, Employee employee) {
		this.department = department;
		this.employee = employee;
	}

}
