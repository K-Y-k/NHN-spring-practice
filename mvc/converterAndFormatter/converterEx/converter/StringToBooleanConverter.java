package com.nhnacademy.springbootmvc.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * String -> boolean으로 변환하도록 직접 구현한 컨버터
 */
public class StringToBooleanConverter implements Converter<String, Boolean> {
    @Override
    public Boolean convert(String source) {
        // TODO 1: yes 는 true, no 는 false 로 변환되도록 구현 그외 문자열은 false
        if (source.equals("true")) {
            return true;
        }

        return false;
    }
}
