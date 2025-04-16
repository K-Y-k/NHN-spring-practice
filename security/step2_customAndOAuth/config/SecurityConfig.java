package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.config;

import com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.service.MemberService;
import com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.auth.CustomAuthenticationFailureHandler;
import com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.auth.CustomAuthenticationSuccessHandler;
import com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.auth.UserAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Componet 빈을 @Configuration인 Config클래스 방식에서도 주입이 가능
     */
//    @Autowired
//    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
//    @Autowired
//    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private final RedisTemplate<String, Object> sessionRedisTemplate;
    private final MemberService memberService;


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

    /**
     * SecurityFilterChain에서 실행할 때 설정한 것을 적용해준다.
     *
     * 예시로)
     * .usernameParameter("id") / .passwordParameter("pwd")로 파라미터명을 지정한 것을
     * UsernamePasswordAuthenticationFilter에서 적용해준다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   @Autowired CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
                                                   @Autowired CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {


        /**
         * 기본으로 csrf 방어가 켜져 있어 잠시 기능을 꺼둠
         * csrf 방어 : 사용자 폼일 때 CSRF 토큰을 부여해주고 폼을 제출할 때 부여했던 CSRF 토큰을 비교함
         */
        http.csrf(AbstractHttpConfigurer::disable);

        /**
         * 권한 없음 오류 발생 시 리다이렉트될 url 설정
         */
        http.exceptionHandling()
                .accessDeniedPage("/403");

        /// UsernamePasswordAuthenticationFilter 전에 수행되는 필터를 추가
        http.addFilterBefore(new UserAuthenticationFilter(sessionRedisTemplate, memberService), UsernamePasswordAuthenticationFilter.class);

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

        /**
         * login 커스텀 설정
         * - 현재 작성한 http.formLogin()은 UsernamePasswordAuthenticationFilter
         *   Spring Security에서 사용자 인증을 처리할 때, 기본 로그인 페이지가 아니라 내가 만든 로그인 페이지를 사용하고,
         * - loginPage("/auth/login") : URL로 로그인 페이지로 이동
         * - loginProcessingUrl("/auth/login/process"):
         * - usernameParameter("id") / passwordParameter("pwd") : 파라미터 이름 설정
         */
        http.formLogin((formLogin) ->
                formLogin.loginPage("/auth/login")
                        .usernameParameter("id")
                        .passwordParameter("pwd")
                        .loginProcessingUrl("/auth/login/process")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
        ).oauth2Login(oauth2 -> oauth2
                /**
                 * O-Auth 설정 추가
                 * OAuth2LoginAuthenticationFilter가 실행되어 이 설정이 적용된다!
                 */        
                .loginPage("/auth/login") // OAuth2 로그인도 같은 로그인 페이지 사용 설정
                .defaultSuccessUrl("/")   // OAuth2 로그인 성공 후 리디렉션 설정
                // redirectUrl 설정은 application.yaml에 있음
        ).authorizeHttpRequests(authorizeRequests ->
                /**
                 * /admin/**           : ADMIN만
                 * /private-project/** : ROLE_ADMIN, ROLE_USER만
                 * /public-project/**  : 인증 없이 모두 가능
                 * /**                 : 인증만 하면 모두 가능
                 *
                 * ("/admin/**").hasRole("ADMIN")인데 hasRole()에 ROLE_PREFIX로 ROLE_이 있어서 ADMIN만 붙인 것이다!
                 * 즉 여기서는 ROLE_을 빼야 한다.
                 */
                /// .anyRequest() 어떤 요청이든 허가로 변경 가능
                authorizeRequests.requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/private-project/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers("/public-project/**").permitAll()
                        .anyRequest().permitAll()
        );

        /// login 기본 설정
        // Customizer.withDefaults()는 기본 형태를 사용할거라는 뜻
        // http.formLogin(Customizer.withDefaults());
        
        return http.build();
    }
}