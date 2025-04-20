package com.nhnacademy.finalsubjectweek03.service.impl;

import com.nhnacademy.finalsubjectweek03.domain.MemberDto;
import com.nhnacademy.finalsubjectweek03.domain.Role;
import com.nhnacademy.finalsubjectweek03.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * OAuth2User로 감싸서 적용할 커스텀 서비스
 */
@Service
@RequiredArgsConstructor
public class CustomOAuth2GoogleUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        /// Redis에 구글 계정 생성
        if (!memberService.exists(email)) {
            memberService.saveMember(new MemberDto(email, name, "", 0, Role.GOOGLE));
        }

        /// GOOGLE 권한 설정
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_GOOGLE"));

        return new DefaultOAuth2User(authorities, attributes, "email");
    }
}