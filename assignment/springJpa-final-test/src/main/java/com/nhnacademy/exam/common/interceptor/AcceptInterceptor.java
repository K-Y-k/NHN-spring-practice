package com.nhnacademy.exam.common.interceptor;

import java.time.LocalDateTime;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AcceptInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String accept = request.getHeader("Accept");
		if (accept.equals("*/*") || accept.equals("application/json") || accept.equals("application/xml")) {
			return true;
		}

		response.setStatus(400);
		response.setContentType("application/json");
		response.addHeader("Connection", "close");

		response.getWriter().write("{\n"
			+ "  \"title\": \"Could not find acceptable representation\",\n"
			+ "  \"status\": 401,\n"
			+ "  \"timestamp\": \"" + LocalDateTime.now() + "\" \n"
			+ "}");

		return false;
	}
}
