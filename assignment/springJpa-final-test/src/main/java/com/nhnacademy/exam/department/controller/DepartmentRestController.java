package com.nhnacademy.exam.department.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhnacademy.exam.department.domain.dto.CreateDepartmentResponse;
import com.nhnacademy.exam.department.domain.dto.CreateDepartmentRequest;
import com.nhnacademy.exam.department.domain.dto.DepartmentMemberResponse;
import com.nhnacademy.exam.department.domain.dto.DepartmentResponse;
import com.nhnacademy.exam.department.domain.dto.UpdateDepartmentRequest;
import com.nhnacademy.exam.department.exception.EmptyParameterException;
import com.nhnacademy.exam.department.exception.MissingParameterException;
import com.nhnacademy.exam.department.service.DepartmentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DepartmentRestController {

	private final DepartmentServiceImpl departmentService;

	@PostMapping("/departments")
	public ResponseEntity<?> createDepartment(@RequestBody CreateDepartmentRequest departmentRequest) {
		CreateDepartmentResponse body = departmentService.saveDepartment(departmentRequest);

		HttpHeaders headers = new HttpHeaders();
		headers.setConnection("keep-alive");
		headers.add("Keep-Alive", "timeout=60");

		return new ResponseEntity<>(body, headers, HttpStatus.CREATED);
	}

	@GetMapping("/departments/{departmentId}")
	public ResponseEntity<?> getDepartment(@PathVariable String departmentId) {
		DepartmentResponse body = departmentService.findDepartmentById(departmentId);

		HttpHeaders headers = new HttpHeaders();
		headers.setConnection("keep-alive");
		headers.add("Keep-Alive", "timeout=60");

		return new ResponseEntity<>(body, headers, HttpStatus.OK);
	}

	@PutMapping("/departments/{departmentId}")
	public ResponseEntity<?> updateDepartment(@PathVariable String departmentId,
		@RequestBody UpdateDepartmentRequest updateDepartmentRequest) {
		departmentService.updateDepartment(departmentId, updateDepartmentRequest);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentLength(0);
		headers.setConnection("keep-alive");
		headers.add("Keep-Alive", "timeout=60");

		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@DeleteMapping("/departments/{departmentId}")
	public ResponseEntity<?> deleteDepartment(@PathVariable String departmentId) {
		departmentService.deleteDepartment(departmentId);

		HttpHeaders headers = new HttpHeaders();
		headers.setConnection("keep-alive");
		headers.add("Keep-Alive", "timeout=60");

		return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/department-members")
	public ResponseEntity<?> getDepartmentMembers(@RequestParam(required = false) String departmentIds) {
		if (Objects.isNull(departmentIds)) {
			throw new MissingParameterException("departmentIds");
		}
		if (departmentIds.isBlank()) {
			throw new EmptyParameterException("departmentIds");
		}

		List<DepartmentMemberResponse> body = departmentService.getDepartmentMembers(departmentIds);

		HttpHeaders headers = new HttpHeaders();
		headers.setConnection("keep-alive");
		headers.add("Keep-Alive", "timeout=60");

		return new ResponseEntity<>(body, headers, HttpStatus.OK);
	}
}
