package com.nhnacademy.finalsubjectweek03.service.impl;

import com.nhnacademy.finalsubjectweek03.domain.SecurityUser;
import com.nhnacademy.finalsubjectweek03.domain.Member;
import com.nhnacademy.finalsubjectweek03.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * UserDetails로 감싸서 적용할 커스텀 서비스
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    /**
     * UserDetails loadUserByUsername(String username)는 패스워드 인증 처리를 해주는 것이 아닌!
     * 받아온 아이디로 우리 서비스에서 인증하는 해당하는 회원 객체를 UserDetail로 감싸서 AuthenticationProvider에게 반환하는 것 뿐이다!
     *
     * AuthenticationProvider에서 UserDetail 정보를 검증하고 성공하면 인증처리를 해준다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /// 받아온 아이디, 패스워드에 해당하는 회원 객체를 우리 시스템의 서비스에서 찾아서
        /// UserDetail로 감싸서 반환
        /// passwordEncoder.encode를 하지 않으면 로그인 처리가 security 내부에서 인코딩하라고 거부한다!
        /// 현재 Redis 저장할 때 passwordEncoder.encode로 저장되어 있어 문제 없음
        Member member = memberService.getMember(username);
        return new SecurityUser(username, member.getPassword(), member.getRole());
    }
}
