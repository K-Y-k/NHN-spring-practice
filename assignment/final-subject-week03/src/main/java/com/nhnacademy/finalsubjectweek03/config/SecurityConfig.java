package com.nhnacademy.finalsubjectweek03.config;

import com.nhnacademy.finalsubjectweek03.auth.*;
import com.nhnacademy.finalsubjectweek03.service.MemberService;
import com.nhnacademy.finalsubjectweek03.service.impl.CustomOAuth2GoogleUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final RedisTemplate<String, Object> sessionRedisTemplate;
    private final MemberService memberService;
    private final CustomOAuth2GoogleUserService customOAuth2GoogleUserService;

    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomLogoutHandler customLogoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        /// csrf 방어가 기본 세팅 값이라 생성, 조회 테스트를 위해 꺼둠
        http.csrf(AbstractHttpConfigurer::disable);

        /// 권한 없음 오류 발생 시 리다이렉트될 url 설정
        http.exceptionHandling()
                .accessDeniedPage("/403");

        /// 기존 로그인 폼, 로그아웃, 페이지 인가 설정
        http.formLogin((formLogin) ->
                formLogin.loginPage("/auth/login")
                        .usernameParameter("id")
                        .passwordParameter("pwd")
                        .loginProcessingUrl("/auth/login/process")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
        ).logout(logout ->
                logout.logoutUrl("/logout")
                        .addLogoutHandler(customLogoutHandler)
        ).authorizeHttpRequests(authorizeRequests ->
                /**
                 * /main/admin/**           : ADMIN만
                 * /main/member/**          : ROLE_MEMBER만
                 * /main/google/**          : ROLE_MEMBER만
                 */
                /// .anyRequest().permitAll() 어떤 요청이든 허가로 적용
                /// .anyRequest().authenticated() 어떤 요청이든 인증되었을 때만 적용
                authorizeRequests.requestMatchers("/main/admin/**").hasRole("ADMIN")
                        .requestMatchers("/main/member/**").hasAnyAuthority("ROLE_MEMBER")
                        .requestMatchers("/main/google/**").hasAnyAuthority("ROLE_GOOGLE")
                        .anyRequest().permitAll()
        );

        /**
         * O-Auth 설정 추가
         * OAuth2LoginAuthenticationFilter가 실행되어 이 설정이 적용된다!
         */
        http.oauth2Login(oauth2 ->
                oauth2.loginPage("/auth/login") // OAuth2 로그인도 같은 로그인 페이지 사용 설정
                        .defaultSuccessUrl("/") // OAuth2 로그인 성공 후 리디렉션 설정
                        .userInfoEndpoint(userInfo
                                -> userInfo.userService(customOAuth2GoogleUserService)
                        )
                        .successHandler(customAuthenticationSuccessHandler)
                        // redirectUrl 설정은 application.yaml에 있음
        );


        /// UsernamePasswordAuthenticationFilter 전에 세션 검증을 수행하는 필터를 추가
        http.addFilterBefore(new UserAuthenticationFilter(sessionRedisTemplate, memberService), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
