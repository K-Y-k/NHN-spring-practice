package com.nhnacademy.exam.department.exception;

public class DepartmentDuplicateException extends RuntimeException {
    public DepartmentDuplicateException(String message) {
        super(message);
    }
}
