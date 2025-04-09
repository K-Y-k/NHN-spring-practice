package com.nhnacademy.springbootmvc.converter;

import com.nhnacademy.springbootmvc.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * String -> Role로 변환하도록 직접 구현한 컨버터
 */
@Component
public class StringToRoleConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        /**
         * Role의 value는 모두 대문자이므로 받다온 문자를 대문자로 적용 후
         * Role의 value로 변환 후 반환하도록 구현
         */
        return Role.valueOf(source.toUpperCase());
    }
}
