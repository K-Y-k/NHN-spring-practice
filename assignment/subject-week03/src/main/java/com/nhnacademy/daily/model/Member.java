package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    /**
     * 현재 서비스에서는 Member로 반환해줄 때만 Member 객체 -> JSON 데이터 변환에만 사용하므로
     * 클래스를 직렬화에 필요한 @JsonSerialize만 사용
     */

    private String id;
    private String name;

    /**
     * JSON은 다음 6가지 기본 타입만 지원한다.
     *
     *   타입	       예시
     * 1.String	    "hello"        	                쌍따옴표로 감싼 문자열
     * 2.Number	     123, 3.14, -42	                정수/실수 모두 가능
     * 3.Boolean	 true, false	                불리언 값
     * 4.Null	     null	                        값이 없음
     * 5.Object 	{"name": "John", "age": 30}  	key-value 쌍 (Map 구조)
     * 6.Array	    [1, 2, 3]	                    값들의 리스트 (배열)
     */
    /// 문자숫자
    @JsonSerialize(using= ToStringSerializer.class)
    private Integer age;

    @JsonProperty("class")
    private String clazz;

    private Locale locale;
}
