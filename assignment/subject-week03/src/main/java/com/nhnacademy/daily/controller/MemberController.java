package com.nhnacademy.daily.controller;

import com.nhnacademy.daily.model.Member;
import com.nhnacademy.daily.model.MemberDto;
import com.nhnacademy.daily.service.MemberServiceRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceRedis memberService;

    @GetMapping("/members/{memberId}")
    public Member getMember(@PathVariable String memberId) {
        return memberService.getMember(memberId);
    }

    @GetMapping("/members")
    public Page<Member> getMembers(Pageable pageable) {
        return memberService.getMembers(pageable);
    }

    @PostMapping("/members")
    public Member saveMember(@RequestBody MemberDto memberDto) {
        return memberService.saveMember(memberDto);
    }
}
