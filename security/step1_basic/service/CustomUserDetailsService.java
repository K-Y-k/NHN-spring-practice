package com.nhnacademy.restcontrollerpractice.security.step1_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserDetailsService의 구현체를
 * InMemoryUserDetailsManager에서 CustomUserDetailsService로 변경
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        if (username.equals("asd")) {
            return new User("asd", passwordEncoder.encode("asd"), List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        } else if (username.equals("zxc")) {
            return new User("zxc", passwordEncoder.encode("zxc"), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }
}
