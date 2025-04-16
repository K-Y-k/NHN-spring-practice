package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.auth;

import com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.domain.Member;
import com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService의 구현체를
 * InMemoryUserDetailsManager에서 CustomUserDetailsService로 변경
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * UserDetails loadUserByUsername(String username)는 패스워드 인증 처리를 해주는 것이 아닌!
     * 받아온 아이디로 우리 서비스에서 인증하는 해당하는 회원 객체를 UserDetail로 감싸서 AuthenticationProvider에게 반환하는 것 뿐이다!
     *
     * AuthenticationProvider에서 UserDetail 정보를 검증하고 성공하면 인증처리를 해준다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * username과 password로 설정하는데
         * username(아이디)이 다르고 비밀번호가 일치하면 로그인이 되는 말도 안되는 버그 발생
         */
        //return new User("admin", passwordEncoder.encode("asd"), Arrays.asList(new SimpleGrantedAuthority("ADMIN")));


        /**
         * 그래서 아이디의 검증에 따라 생성해서 반환
         */
        // if (username.equals("admin")) {
        //     return new User("admin", passwordEncoder.encode("admin"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        // } else if (username.equals("user")) {
        //     return new User("user", passwordEncoder.encode("user"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        // }

        /// 주로 사용되는 방식
        /// 받아온 아이디, 패스워드에 해당하는 회원 객체를 우리 시스템의 서비스에서 찾아서
        /// UserDetail로 감싸서 반환
        /// passwordEncoder.encode를 하지 않으면 로그인 처리가 security 내부에서 인코딩하라고 거부한다!
        Member member = memberService.getMember(username);
        return new AcademyUser(username, passwordEncoder.encode(member.getPassword()), member.getRole());
    }
}
