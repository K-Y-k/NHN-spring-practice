package com.nhnacademy.finalsubjectweek03.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Slf4j
public class PageableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    /// 페이지에 기본값은 page=0, size = 5이고 max size는 10으로 설정
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 5;
    private static final int MAX_SIZE = 10;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        /// equals 메서드는 두 클래스가 정확히 동일한 클래스인지 비교
        /// 받아온 파라미터 타입이 Pageable 클래스인 확인
        return Pageable.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("PageableHandlerMethodArgumentResolver 실행");

        // 파라미터 가져오기
        String pageParam = webRequest.getParameter("page");
        String sizeParam = webRequest.getParameter("size");
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : DEFAULT_PAGE;
        int size = (sizeParam != null) ? Integer.parseInt(sizeParam) : DEFAULT_SIZE;

        // 최대 크기 제한
        if (size > MAX_SIZE) {
            log.info("size가 초과하여 MAX_SIZE로 적용");
            size = MAX_SIZE;
        }

        // 제한한 Pageable 구현체 PageRequest로 반환
        return PageRequest.of(page, size);
    }
}