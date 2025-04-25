package com.nhnacademy.exam.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhnacademy.exam.department.domain.entity.DepartmentMember;

public interface DepartmentMemberJpaRepository extends JpaRepository<DepartmentMember, String> {
	List<DepartmentMember> findByDepartment_Id(String departmentDepartmentId);
}
