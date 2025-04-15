package com.nhnacademy.restcontrollerpractice.security.step1_basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * 권한 계층 구조 설정법
     */
    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }
    @Bean
    public RoleHierarchy roleHierarchy() {
        /// ROLE_ADMIN 은 ROLE_USER 보다 상위로 설정
        /// 우선순위를 지정한 것이 아닌 ROLE_USER까지의 권한이 포함되는 것이다!
        return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > ROLE_USER");
    }

    /**
     * UserDetailService의 InMemoryUserDetailsManager 구현체
     */
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        // 관리자 계정
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("asd")
//                .password("asd")
//                .roles("ADMIN")
//                .build();
//
//        // 회원 계정
//        UserDetails member = User.withDefaultPasswordEncoder()
//                .username("zxc")
//                .password("zxc")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, member);
//    }

    /**
     * CustomUserDetailsService에서 PasswordEncoder 사용하기 위해 등록
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         * anyRequest().authenticated()로 모든 요청은 인증이 필요하다고 설정 방법
         */
        //http.authorizeHttpRequests(authorizeRequests ->
        //        authorizeRequests
        //                .anyRequest().authenticated()
        //);

        /**
         * 각 경로마다 권한 지정 방법
         */
        http.authorizeHttpRequests(authorizeRequests ->
                /**
                 * /admin/**           : ADMIN만
                 * /private-project/** : ROLE_ADMIN, ROLE_USER만
                 * /public-project/**  : 인증 없이 모두 가능
                 * /**                 : 인증만 하면 모두 가능
                 *
                 * ("/admin/**").hasRole("ADMIN")인데 hasRole()에 ROLE_PREFIX로 ROLE_이 있어서 ADMIN만 붙인 것이다!
                 * 즉 여기서는 ROLE_을 빼야 한다.
                 */
                authorizeRequests.requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/private-project/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers("/public-project/**").permitAll()
                        .anyRequest().authenticated()
        );

        // login
        // Customizer.withDefaults()는 기본 형태를 사용할거라는 뜻
        http.formLogin(Customizer.withDefaults());
        return http.build();
    }
}