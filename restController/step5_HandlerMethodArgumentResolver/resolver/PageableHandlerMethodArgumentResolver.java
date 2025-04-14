package com.nhnacademy.restcontrollerpractice.step5.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 30;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Pageable.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        System.out.println("PageableHandlerMethodArgumentResolver 실행");

        /**
         * 최대 size 적용 로직
         */
        
        // 파라미터 가져오기
        String pageParam = webRequest.getParameter("page");
        String sizeParam = webRequest.getParameter("size");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : DEFAULT_PAGE;
        int size = (sizeParam != null) ? Integer.parseInt(sizeParam) : DEFAULT_SIZE;

        // 최대 크기 제한
        if (size > MAX_SIZE) {
            System.out.println("size가 초과하여 MAX_SIZE로 적용");
            size = MAX_SIZE;
        }

        // 제한한 Pageable 구현체 PageRequest로 반환
        return PageRequest.of(page, size);
    }
}
