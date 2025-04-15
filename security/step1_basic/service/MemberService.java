//package com.nhnacademy.restcontrollerpractice.security.step1_basic;
//
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service("memberService2")
//public class MemberService {
//    private Map<String, Member> memberMap = new HashMap<>();
//
//    public MemberService() {
//        memberMap.put("asd", new Member("asd", "asd", "name1"));
//        memberMap.put("zxc", new Member("zxc", "zxc",  "name2"));
//    }
//
//    public Member login(MemberLoginRequest loginRequest) {
//        for (Member member : memberMap.values()) {
//            if (member.getPassword().equals(loginRequest.getPassword())) {
//                return member;
//            }
//        }
//
//        return null;
//    }
//
//}
