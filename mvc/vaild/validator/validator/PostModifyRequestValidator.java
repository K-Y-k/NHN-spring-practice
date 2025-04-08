package com.nhnacademy.springbootmvc.validator;

import com.nhnacademy.springbootmvc.domain.PostModifyRequest;
import com.nhnacademy.springbootmvc.domain.PostRegisterRequest;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * 1.빈으로 등록하고 Validator 인터페이스를 구현 받음
 */
@Component
public class PostModifyRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        /**
         * 2.검증하는 dto 객체 설정
         */
        return PostModifyRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        /**
         * 3.NotBlank와 같은 검증 처리
         */
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "", "title is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "", "content is empty");

        PostModifyRequest request = (PostModifyRequest) target;
        String content = request.getContent();
        if (content.length() > 5) {
            errors.rejectValue("content", "", "content max length is 5");
        }
    }
}
