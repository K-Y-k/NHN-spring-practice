package com.nhnacademy.exam.common.config;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.nhnacademy.exam.department.domain.dto.CreateDepartmentRequest;
import com.nhnacademy.exam.department.domain.entity.Department;
import com.nhnacademy.exam.department.domain.entity.DepartmentMember;
import com.nhnacademy.exam.department.service.DepartmentServiceImpl;
import com.nhnacademy.exam.employee.domain.Employee;
import com.nhnacademy.exam.employee.service.EmployeeServiceImpl;
import com.nhnacademy.exam.parser.DepartmentParser;
import com.nhnacademy.exam.parser.DepartmentParserResolver;
import com.nhnacademy.exam.parser.ParsingObject;
import com.nhnacademy.exam.parser.impl.CsvDepartmentParser;
import com.nhnacademy.exam.parser.impl.JsonDepartmentParser;
import com.nhnacademy.exam.parser.impl.TextDepartmentParser;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataConfig {

	private final DepartmentParserResolver departmentParserResolver;

	private final EmployeeServiceImpl employeeService;
	private final DepartmentServiceImpl departmentService;


	@PostConstruct
	public void init() throws IOException {
		/// resources/data 폴더 안에 있는 파일들 모두 가져옴
		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:data/*");

		/// 리소스 파일들을 모두 조회하며 파싱하고 db에 저장
		for (Resource resource : resources) {
			DepartmentParser departmentParser = departmentParserResolver.getDepartmentParser(String.valueOf(resource.getFile()));

			log.info("DataConfig - resource: {}", resource.getFilename());
			log.info("DataConfig - Department Parser: {}", departmentParser.getClass());

			/// JsonParser는 적용 못함
			if (departmentParser.getClass().equals(CsvDepartmentParser.class) || departmentParser.getClass().equals(TextDepartmentParser.class)) {
				List<ParsingObject> parsingObjects = departmentParser.parsing(resource.getFile());
				for (ParsingObject parsingObject : parsingObjects) {
					Employee employee = new Employee(parsingObject.getEmployeeId(), parsingObject.getEmployeeName());
					CreateDepartmentRequest createDepartment = new CreateDepartmentRequest(parsingObject.getDepartmentId(), parsingObject.getDepartmentName());

					if (!employeeService.exist(parsingObject.getEmployeeId())) {
						employeeService.save(employee);
					}

					if (!departmentService.exist(parsingObject.getDepartmentId())) {
						departmentService.saveDepartment(createDepartment);
					}

					departmentService.saveDepartmentMember(new DepartmentMember(new Department(createDepartment.getId(), createDepartment.getName()), employee));
					log.info("DataConfig - JsonDepartmentParser - save ok {}", parsingObject);
				}
			}

		}

	}
}
