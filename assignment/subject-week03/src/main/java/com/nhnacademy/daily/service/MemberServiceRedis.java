package com.nhnacademy.daily.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.daily.exception.MemberAlreadyExistsException;
import com.nhnacademy.daily.exception.MemberNotFoundException;
import com.nhnacademy.daily.messenger.DoorayMessengerRequest;
import com.nhnacademy.daily.messenger.MessengerClient;
import com.nhnacademy.daily.model.Locale;
import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.MemberDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MemberServiceRedis {
    @Autowired
    MessengerClient messengerClient;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private final String MEMBER_HASH_NAME = "Member:";


    @PostConstruct
    public void initData() {
//        saveMember(new MemberDto("1", "kyk",  10, "A", Locale.KO));
//        saveMember(new MemberDto("baek", "백종원",  20, "B", Locale.EN));
//        saveMember(new MemberDto("ujeae", "유재석",  30, "C", Locale.JP));
    }


    public Member saveMember(MemberDto memberDto) {
        if (redisTemplate.opsForHash().hasKey(MEMBER_HASH_NAME, memberDto.getId())) {
            throw new MemberAlreadyExistsException("Member already exists.");
        }

        Member createMember = new Member(memberDto.getId(), memberDto.getName(), memberDto.getAge(), memberDto.getClazz(), memberDto.getLocale());

        // opsForHash : Hash를 위한 operator
        redisTemplate.opsForHash().put(MEMBER_HASH_NAME, createMember.getId(), createMember);

        DoorayMessengerRequest request = new DoorayMessengerRequest(createMember.getName(), createMember.getName() + "님이 생성되었습니다.");
        messengerClient.sendNotification(request);

        return createMember;
    }

    public Member getMember(String memberId) {
        /**
         * Hash를 위한 operator로 저장했으므로
         * opsForHash에서 가져와야 한다.
         */
        Object o = redisTemplate.opsForHash().get(MEMBER_HASH_NAME, memberId);
        if (Objects.isNull(o)) {
            throw new MemberNotFoundException("Member not found");
        }

        /**
         * 만약 @class 부분을 지웠다면 어떤 클래스인지 redis가 판별하지 못하므로
         * ObjectMapper 활용
         */
        return objectMapper.convertValue(o, Member.class);
    }

    public Page<Member> getMembers(Pageable pageable) {
        // 리스트로 가공
        List<Member> memberList = new ArrayList<>();

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(MEMBER_HASH_NAME);
        for (Object o : entries.values()) {
            Member member = objectMapper.convertValue(o, Member.class);
            memberList.add(member);
        }

        // 리스트를 페이징 처리
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), memberList.size());
        List<Member> pagedList = memberList.subList(start, end);

        return new PageImpl<>(pagedList, pageable, memberList.size());
    }


}
