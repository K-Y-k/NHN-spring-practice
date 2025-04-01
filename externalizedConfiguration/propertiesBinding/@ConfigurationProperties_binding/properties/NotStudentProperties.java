package org.example.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 필드에 properties에 등록한 값으로 넣으려면
 * @ConfigurationProperties()를 사용
 * 조건1.setter 또는 생성자가 있어야 한다.
 * 조건2.@Configuration이 있는 클래스에 @EnableConfigurationProperties(StudentProperties.class) 등록해줘야함
 *
 * 필드에 값을 받은 빈처럼 생성
 */
//TODO-1 Externalized Configuration 선언
@Getter
@AllArgsConstructor
@ConfigurationProperties("notstudent")
public class NotStudentProperties {
    String firstName;
}
