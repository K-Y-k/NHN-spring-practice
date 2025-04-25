package com.nhnacademy.exam.common.error.dto;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class GlobalErrorResponse {
	String title;
	int status;
	LocalDateTime timeStamp;
}
