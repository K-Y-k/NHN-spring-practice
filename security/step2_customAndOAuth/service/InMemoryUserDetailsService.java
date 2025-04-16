//package com.nhnacademy.restcontrollerpractice.security;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InMemoryUserDetailsService extends InMemoryUserDetailsManager {
//    public InMemoryUserDetailsService() {
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
//        this.createUser(admin);
//        this.createUser(member);
//    }
//}
