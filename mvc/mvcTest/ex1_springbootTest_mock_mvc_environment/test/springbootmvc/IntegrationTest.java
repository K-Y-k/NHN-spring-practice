package com.nhnacademy.springbootmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @SpringBootTest 를 사용하면 애플리케이션의 설정, 모든 빈을 로드하기 때문에 시간이 오래걸림
 * - 애플리케이션의 설정, 모든 빈을 로드함
 * - 주로 통합테스트를 위해서 사용
 * - 웹테스트 환경을 선택 가능
 *    - WebEnvironment.MOCK
 *      : 서블릿 컨테이너를 실행하지 않고 서블릿을 mock으로 만들어 테스트,
 *        MockMvc 객체를 사용하여 스프링 MVC 기능 테스트 수행
 *
 *    - WebEnvironment.RANDOM_PORT
 *      : 서블릿 컨테이너 실행, 포트는 랜텀값
 *    - WebEnvironment.DEFINED_PORT
 *      : application.properties에 정의 된 포트를 사용하여 서블릿 컨테이너 실행
 *    - WebEnvironment.NONE
 *      : 서블릿 환경을 구성하지 않고 테스트를 실행 (Web이 아닌 일반 서비스 테스트용)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                                                  String.class)).contains("Hello");
        ResponseEntity<String> res = this.restTemplate.getForEntity("http://localhost:" + port + "/",
                                                                          String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody().toString()).contains("Hello");
        System.out.println(res);
    }

    /**
     * user 도메인 관련 테스트 예시
     */
    @Test
    void getUsersTest() throws Exception {
        ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:" + port + "/users", String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getBody()).contains("tom");
        assertThat(res.getBody()).contains("jake");
        assertThat(res.getBody()).contains("ethan");
    }

    
}
