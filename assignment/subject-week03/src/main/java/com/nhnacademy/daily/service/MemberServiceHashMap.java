//package com.nhnacademy.daily.service;
//
//import com.nhnacademy.daily.exception.MemberAlreadyExistsException;
//import com.nhnacademy.daily.messenger.DoorayMessengerRequest;
//import com.nhnacademy.daily.messenger.MessengerClient;
//import com.nhnacademy.daily.model.Locale;
//import com.nhnacademy.daily.model.Member;
//import com.nhnacademy.daily.model.MemberDto;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class MemberServiceHashMap {
//    @Autowired
//    MessengerClient messengerClient;
//
//    private final Map<String, Member> memberMap = new HashMap<>();
//
//
//    @PostConstruct
//    public void init() {
//        saveMember(new MemberDto("1", "유재석", 30, "A", Locale.KO));
//        saveMember(new MemberDto("baek", "백종원", 45, "B", Locale.EN));
//        saveMember(new MemberDto("kyk", "kyk", 25, "C", Locale.JP));
//    }
//
//
//    public Member saveMember(MemberDto memberDto) {
//        if (memberMap.containsKey(memberDto.getId())) {
//            throw new MemberAlreadyExistsException("Member already exists.");
//        }
//
//        Member createMember = new Member(memberDto.getId(), memberDto.getName(), memberDto.getAge(), memberDto.getClazz(), memberDto.getLocale());
//        // put은 새로 넣은 값이 반환되는 것이 아니다.
//        // 새로 넣은 것이면 null로 반환된다.
//        memberMap.put(createMember.getId(), createMember);
//
//        DoorayMessengerRequest request = new DoorayMessengerRequest(createMember.getName(), createMember.getName() + "님이 생성되었습니다.");
//        messengerClient.sendNotification(request);
//
//        return createMember;
//    }
//
//    public Member getMember(String id){
//        return memberMap.get(id);
//    }
//
//    public Page<Member> getMembers(Pageable pageable) {
//        // 리스트로 가공
//        List<Member> memberList = new ArrayList<>(memberMap.values());
//
//        // 리스트를 페이징 처리
//        int start = (int) pageable.getOffset();
//        int end = Math.min(start + pageable.getPageSize(), memberList.size());
//        List<Member> pagedList = memberList.subList(start, end);
//
//        return new PageImpl<>(pagedList, pageable, memberList.size());
//    }
//}
