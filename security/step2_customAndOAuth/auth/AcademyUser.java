package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.auth;

import com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AcademyUser implements UserDetails {
    private String userName;
    private String password;
    private Role role;

    //TODO
    public AcademyUser(String userName, String password, Role role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_" + this.role;
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }
}
