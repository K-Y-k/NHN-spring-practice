package com.nhnacademy.exam.parser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParsingObject {
	private String employeeId;
	private String employeeName;
	private String departmentName;
	private String departmentId;
}
