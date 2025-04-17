package com.nhnacademy.finalsubjectweek03.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.finalsubjectweek03.domain.Member;
import com.nhnacademy.finalsubjectweek03.domain.MemberDto;
import com.nhnacademy.finalsubjectweek03.domain.Role;
import com.nhnacademy.finalsubjectweek03.exception.MemberAlreadyExistsException;
import com.nhnacademy.finalsubjectweek03.exception.MemberNotFoundException;
import com.nhnacademy.finalsubjectweek03.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceRedisImpl implements MemberService {
    private final ObjectMapper reidsObjectMapper;  /// @class 속성을 지우기 위해 사용
    private final RedisTemplate<String, Object> memberRedisTemplate;
    private final PasswordEncoder passwordEncoder; /// 비밀번호 암호화 하기 위해 사용
    private final String HASH_NAME = "Member:";

    @PostConstruct
    public void initData() {
//        saveMember(new MemberDto("1", "usaesheock", "usaePwd", 20, Role.MEMBER));
//        saveMember(new MemberDto("2", "baek", "baekPwd", 20, Role.MEMBER));
//        saveMember(new MemberDto("3", "shin", "shinPwd", 20, Role.MEMBER));
//        saveMember(new MemberDto("qwe", "qwe", "qwe", 0, Role.ADMIN));
//        saveMember(new MemberDto("asd", "asd", "asd", 0, Role.MEMBER));
//        saveMember(new MemberDto("zxc", "zxc", "zxc", 0, Role.GOOGLE));
    }


    @Override
    public Member saveMember(MemberDto memberDto) {
        if (memberRedisTemplate.opsForHash().hasKey(HASH_NAME, memberDto.getId())) {
            throw new MemberAlreadyExistsException("Member already exists");
        }

        Member createMember = new Member(memberDto.getId(), memberDto.getName(), passwordEncoder.encode(memberDto.getPassword()), memberDto.getAge(), memberDto.getRole());
        memberRedisTemplate.opsForHash().put(HASH_NAME, memberDto.getId(), createMember);
        return createMember;
    }

    @Override
    public Member getMember(String memberId) {
        if (!memberRedisTemplate.opsForHash().hasKey(HASH_NAME, memberId)) {
            throw new MemberNotFoundException("Member not found");
        }

        Object findMemberObject = memberRedisTemplate.opsForHash().get(HASH_NAME, memberId);
        return reidsObjectMapper.convertValue(findMemberObject, Member.class);
    }

    @Override
    public Page<Member> getMembers(Pageable pageable) {
        List<Member> memberList = new ArrayList<>();
        memberRedisTemplate.opsForHash().entries(HASH_NAME).forEach((key, value) -> {
            memberList.add(reidsObjectMapper.convertValue(value, Member.class));
        });

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), memberList.size());
        List<Member> pagedList = memberList.subList(start, end);

        return new PageImpl<>(pagedList, pageable, memberList.size());
    }
}
