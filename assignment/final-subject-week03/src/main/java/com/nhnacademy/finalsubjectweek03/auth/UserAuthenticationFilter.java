package com.nhnacademy.finalsubjectweek03.auth;

import com.nhnacademy.finalsubjectweek03.domain.Member;
import com.nhnacademy.finalsubjectweek03.domain.SecurityUser;
import com.nhnacademy.finalsubjectweek03.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, Object> sessionRedisTemplate;
    private final MemberService memberService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /// 쿠키에서 sessionId가 있는지 찾고
        String sessionId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSIONID".equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                }
            }
        }

        /// sessionId가 있으면
        if (Objects.nonNull(sessionId)) {
            /// Redis에서 해당 sessionId의 userName 값을 가져와서
            Object o = sessionRedisTemplate.opsForValue().get(sessionId);
            String userName = (String) o;

            /// userName이 있으면
            if (!StringUtils.isEmpty(userName)) {
                Member member = memberService.getMember(userName);
                SecurityUser securityUser = new SecurityUser(member.getId(), member.getPassword(), member.getRole());

                /// 인증 객체를 직접 가공
                Authentication auth = new PreAuthenticatedAuthenticationToken(
                        securityUser,                     // Principal (인증 주체)
                        null,                             // Credentials (비밀번호 등, 이미 인증됐으므로 null)
                        securityUser.getAuthorities()     // 권한 목록
                );
                auth.setAuthenticated(true);             // 스프링에 "이미 인증된 사용자다" 라고 설정

                log.info("UserAuthenticationFilter authenticated ROLE = {}", auth.getAuthorities());

                /// 가공한 인증을 SecurityContextHolder에 등록
                /// 현재 스레드의 SecurityContext에 인증(Authentication) 객체를 등록한다!
                /// 이 사용자(auth)가 인증되었고, 권한과 주체(principal)를 Spring Security에 명시적으로 알려주는 것
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        /// 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }

}