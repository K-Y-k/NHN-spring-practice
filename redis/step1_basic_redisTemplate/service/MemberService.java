package com.nhnacademy.restcontrollerpractice.redis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.restcontrollerpractice.redis.domain.Member;
import com.nhnacademy.restcontrollerpractice.redis.domain.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private String HASH_NAME = "Member:";

    public Member saveMember(MemberDto memberDto) {
        //TODO
        // 중복체크 Error
        if (redisTemplate.opsForHash().hasKey(HASH_NAME, memberDto.getId())) {
            Object o = redisTemplate.opsForHash().get(HASH_NAME, memberDto.getId());
            return objectMapper.convertValue(o, Member.class);
        }

        Member createMember = new Member(memberDto.getId(), memberDto.getName(), memberDto.getAge(), memberDto.getRole(), memberDto.getClazz());

        // opsForHash : Hash를 위한 operator
        redisTemplate.opsForHash().put(HASH_NAME, createMember.getId(), createMember);

        return createMember;
    }

    public Member getMember(String memberId) {
        /**
         * Hash를 위한 operator로 저장했으므로
         * opsForHash에서 가져와야 한다.
         */
        Object o = redisTemplate.opsForHash().get(HASH_NAME, memberId);
        if (o == null) {
            System.out.println("Member not found");
        }

        /**
         * 만약 @class 부분을 지웠다면 어떤 클래스인지 redis가 판별하지 못하므로
         * ObjectMapper 활용
         */
        return objectMapper.convertValue(o, Member.class);
    }

    public List<Member> getMembers() {
        List<Member> memberList = new ArrayList<>();

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(HASH_NAME);
        for (Object o : entries.values()) {
            Member member = objectMapper.convertValue(o, Member.class);
            memberList.add(member);
        }
        return memberList;

//        return entries.values().stream()
//                .map(obj -> (Member) obj)
//                .collect(Collectors.toList());
    }

    public void deleteMember(String id) {
        redisTemplate.opsForHash().delete(HASH_NAME, id);
    }

    public Member updateMember(MemberDto memberDto) {
        Member updateMember = new Member(memberDto.getId(), memberDto.getName(), memberDto.getAge(), memberDto.getRole(), memberDto.getClazz());
        redisTemplate.opsForHash().put(HASH_NAME, updateMember.getId(), updateMember);
        return updateMember;
    }

}
