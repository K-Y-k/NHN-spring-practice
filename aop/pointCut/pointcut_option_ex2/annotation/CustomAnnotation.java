package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Retention으로 RUNTIME에만 어노테이션 사용 제한
 * @Target으로 메소드 위에만 이 어노테이션을 붙일 수 있게 제한
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {
}
