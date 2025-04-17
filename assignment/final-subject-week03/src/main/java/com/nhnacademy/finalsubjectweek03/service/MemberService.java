package com.nhnacademy.finalsubjectweek03.service;

import com.nhnacademy.finalsubjectweek03.domain.Member;
import com.nhnacademy.finalsubjectweek03.domain.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
    Member saveMember(MemberDto memberDto);
    Member getMember(String memberId);
    Page<Member> getMembers(Pageable pageable);
}
