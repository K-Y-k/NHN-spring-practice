package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.auth;

import com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.domain.Member;
import com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

public class UserAuthenticationFilter extends OncePerRequestFilter {

    private RedisTemplate<String, Object> sessionRedisTemplate;

    private MemberService memberService;
    public UserAuthenticationFilter(RedisTemplate<String, Object> sessionRedisTemplate, MemberService memberService) {
        this.sessionRedisTemplate = sessionRedisTemplate;
        this.memberService = memberService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //TODO
        String sessionId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSIONID".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                }
            }
        }

        if (Objects.nonNull(sessionId)) {
            // Redis에서 해당 sessionId의 username 값을 가져와서
            Object o = sessionRedisTemplate.opsForValue().get(sessionId);
            String userName = (String) o;

            /// Redis에 해당 sessionId가 있으면
            if (!StringUtils.isEmpty(userName)) {
                Member member = memberService.getMember(userName);
                AcademyUser academyUser = new AcademyUser(member.getId(), member.getPassword(), member.getRole());

                /// 인증 객체를 직접 가공
                /// 인증 처리를 여기서 한번에 직접 처리했지만
                /// 보통 OAuth2, JWT 등 토큰 기반 인증을 사용하는 경우, 인증 처리는 토큰을 위임하여 처리하는 방식이 일반적이다.
                Authentication auth = new PreAuthenticatedAuthenticationToken(
                        academyUser,                     // Principal (인증 주체)
                        null,                            // Credentials (비밀번호 등, 이미 인증됐으므로 null)
                        academyUser.getAuthorities()     // 권한 목록
                );
                auth.setAuthenticated(true); // 스프링에 "이미 인증된 사용자다" 라고 설정
                                             // 정말 인증이 확실히 완료된 경우에만 해야 한다.
                                             // 그렇지 않으면 보안상 위험한 우회 경로가

                /// 가공한 인증을 SecurityContextHolder에 등록
                /// 현재 스레드의 SecurityContext에 인증(Authentication) 객체를 등록한다!
                /// 이 사용자(auth)가 인증되었고, 권한은 이렇고, 주체(principal)는 이거야를 Spring Security에 명시적으로 알려주는 것
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }


        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }

}