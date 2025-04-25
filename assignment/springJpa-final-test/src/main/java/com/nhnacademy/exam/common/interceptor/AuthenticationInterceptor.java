package com.nhnacademy.exam.common.interceptor;

import java.time.LocalDateTime;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String xUserId = request.getHeader("X-USER-ID");
		if (StringUtils.equals(xUserId, "nhnacademy")) {
			return true;
		}

		response.setStatus(401);
		response.setContentType("application/json");
		response.addHeader("Connection", "keep-alive");
		response.addHeader("Keep-Alive", "timeout=60");

		response.getWriter().write("{\n"
			+ "  \"title\": \"Unauthorized\",\n"
			+ "  \"status\": 401,\n"
			+ "  \"timestamp\": \"" + LocalDateTime.now() + "\" \n"
			+ "}");

		return false;
	}
}

