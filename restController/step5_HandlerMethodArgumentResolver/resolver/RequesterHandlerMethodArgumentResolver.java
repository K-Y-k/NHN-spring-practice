package com.nhnacademy.restcontrollerpractice.step5.resolver;

import com.nhnacademy.restcontrollerpractice.step5.domain.Requester;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.LocaleResolver;

public class RequesterHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private LocaleResolver localeResolver;
    public RequesterHandlerMethodArgumentResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    /**
     * Requester 클래스가 인자일 때 이 RequesterResolver가 실행된다.
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Requester.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        System.out.println("RequesterHandlerMethodArgumentResolver 실행");

        // 로직
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        return new Requester(httpServletRequest.getRemoteAddr(), localeResolver.resolveLocale(httpServletRequest));
    }
}
