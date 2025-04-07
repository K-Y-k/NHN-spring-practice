package com.nhnacademy.springbootmvc.domain;

import lombok.Value;

/**
 * @Value 어노테이션은 @Data 어노테이션의 변형된 녀석인데, 
 * 특이한 점은 객체를 불변(Immutable)으로 만들어준다는 점이다.
 *
 * 불변이란, 객체 생성 후 그 상태를 수정할 수 없는 것을 뜻한다.
 * 그렇기 때문에 객체의 상태를 변경할 수 있는 @Setter는 생성되지 않는다.
 */
// TODO #5: 사용자 등록 요청 객체
@Value
public class UserRegisterRequest {
    /**
     * 파라미터로 받아온 변수명과 동일한 변수명으로 갖춰야 한다.
     * 누락된 필드가 있으면 누락된 필드만 기본값이 나온다.
     */
    String id;
    String password;
    int age;
}
