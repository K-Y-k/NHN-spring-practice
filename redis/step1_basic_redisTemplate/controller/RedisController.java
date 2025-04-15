package com.nhnacademy.restcontrollerpractice.redis.controller;

import com.nhnacademy.restcontrollerpractice.redis.domain.Member;
import com.nhnacademy.restcontrollerpractice.redis.domain.MemberDto;
import com.nhnacademy.restcontrollerpractice.redis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RedisController {
    private final MemberService memberService;

    /**
     * 멤버를 생성하는 API
     */
    @PostMapping("/members")
    public Member saveMember(@RequestBody MemberDto memberDto) {
        return memberService.saveMember(memberDto);
    }

    /**
     * 멤버 단건을 조회하는 API
     */
    @GetMapping("/members/{memberId}")
    public Member getMember(@PathVariable String memberId) {
        Member getMember = memberService.getMember(memberId);
        return getMember;
    }

    /**
     * 멤버 전체를 가져오는 API
     */
    @GetMapping("/members")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

    /**
     * 멤버를 삭제하는 API
     */
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable String memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }

    /**
     * 멤버를 수정하는 API
     */
    @PutMapping("/members/{memberId}")
    public Member updateMember(@PathVariable String memberId, @RequestBody MemberDto memberDto) {
        return memberService.updateMember(memberDto);
    }
}

