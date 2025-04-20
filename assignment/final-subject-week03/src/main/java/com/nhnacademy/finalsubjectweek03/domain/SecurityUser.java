package com.nhnacademy.finalsubjectweek03.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class SecurityUser implements UserDetails {
    private final String userName;
    private final String password;
    private final Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /// 현재 redis에 저장된 role의 값이 소문자이므로 대문자 적용
        String role = "ROLE_" + this.role.toString().toUpperCase();
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
