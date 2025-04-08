package com.nhnacademy.springbootmvc.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class ProcessTimeInterceptor implements HandlerInterceptor {

    /**
     * 필드로 선언하면 모든 요청에 대해 설정될 수 있으므로
     * 현실은 여러 동시 요청이 일어나므로
     * 다른 요청이 더럽히지 않도록 request.setAttribute()를 사용한다.
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long start = (long) request.getAttribute("start");
        long end = System.currentTimeMillis();

        log.info("실행 시간 : {}", end - start);

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
