package com.nhnacademy.finalsubjectweek03.controller;

import com.nhnacademy.finalsubjectweek03.domain.Member;
import com.nhnacademy.finalsubjectweek03.domain.MemberDto;
import com.nhnacademy.finalsubjectweek03.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;

    @PostMapping("/members")
    public Member saveMember(@RequestBody MemberDto memberDto) {
        return memberService.saveMember(memberDto);
    }

    @GetMapping("/members/{memberId}")
    public Member getMember(@PathVariable String memberId) {
        return memberService.getMember(memberId);
    }

    @GetMapping("/members")
    public Page<Member> getMembers(Pageable pageable) {
        return memberService.getMembers(pageable);
    }
}
