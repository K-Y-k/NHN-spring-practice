package com.nhnacademy.exam.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nhnacademy.exam.department.domain.dto.CreateDepartmentRequest;
import com.nhnacademy.exam.department.domain.dto.CreateDepartmentResponse;
import com.nhnacademy.exam.department.domain.dto.DepartmentMemberResponse;
import com.nhnacademy.exam.department.domain.dto.DepartmentResponse;
import com.nhnacademy.exam.department.domain.dto.UpdateDepartmentRequest;
import com.nhnacademy.exam.department.domain.entity.Department;
import com.nhnacademy.exam.department.domain.entity.DepartmentMember;
import com.nhnacademy.exam.department.exception.DepartmentDuplicateException;
import com.nhnacademy.exam.department.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.department.repository.DepartmentJpaRepository;
import com.nhnacademy.exam.department.repository.DepartmentMemberJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl {

	private final DepartmentJpaRepository departmentRepository;
	private final DepartmentMemberJpaRepository departmentMemberRepository;

	public CreateDepartmentResponse saveDepartment(CreateDepartmentRequest departmentRequest) {
		Optional<Department> findDepartment = departmentRepository.findById(departmentRequest.getId());
		if (findDepartment.isPresent()) {
			throw new DepartmentDuplicateException("부서 아이디 중복 : " + departmentRequest.getId());
		}

		Department saveDepartment = departmentRepository.save(new Department(departmentRequest.getId(), departmentRequest.getName()));
		return new CreateDepartmentResponse(saveDepartment.getId());
	}

	public DepartmentResponse findDepartmentById(String departmentId) {
		Department findDepartment = departmentRepository.findById(departmentId)
			.orElseThrow(() -> new DepartmentNotFoundException("department not found : " + departmentId));

		return new DepartmentResponse(findDepartment.getId(), findDepartment.getName());
	}

	public void updateDepartment(String departmentId, UpdateDepartmentRequest updateDepartmentRequest) {
		Department findDepartment = departmentRepository.findById(departmentId)
			.orElseThrow(() -> new DepartmentNotFoundException(departmentId));

		findDepartment.setName(updateDepartmentRequest.getName());
	}

	public void deleteDepartment(String departmentId) {
		departmentRepository.deleteById(departmentId);
	}

	public boolean exist(String id){
		return departmentRepository.findById(id).isPresent();
	}

	public void saveDepartmentMember(DepartmentMember departmentMember) {
		departmentMemberRepository.save(departmentMember);
	}

	public List<DepartmentMemberResponse> getDepartmentMembers(String departmentIds) {
		List<DepartmentMemberResponse> result = new ArrayList<>();

		if (departmentIds.contains(",")) {
			String[] splitDepartmentId = departmentIds.split(",");
			for (String departmentId : splitDepartmentId) {
				List<DepartmentMember> departmentMemberList = departmentMemberRepository.findByDepartment_Id(departmentId);
				for (DepartmentMember departmentMember : departmentMemberList) {
					result.add(new DepartmentMemberResponse(departmentMember.getDepartment(), departmentMember.getEmployee()));
				}
			}
		} else {
			List<DepartmentMember> departmentMemberList = departmentMemberRepository.findByDepartment_Id(departmentIds);
			for (DepartmentMember departmentMember : departmentMemberList) {
				result.add(new DepartmentMemberResponse(departmentMember.getDepartment(), departmentMember.getEmployee()));
			}
		}

		return result;
	}
}
