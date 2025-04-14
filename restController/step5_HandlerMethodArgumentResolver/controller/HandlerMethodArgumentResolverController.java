package com.nhnacademy.restcontrollerpractice.step5.controller;

import com.nhnacademy.restcontrollerpractice.step4.domain.ClassType;
//import org.springframework.data.domain.Pageable;
import com.nhnacademy.restcontrollerpractice.step5.domain.Member;
import com.nhnacademy.restcontrollerpractice.step5.domain.MemberDto;
import com.nhnacademy.restcontrollerpractice.step5.domain.Requester;
import com.nhnacademy.restcontrollerpractice.step5.domain.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HandlerMethodArgumentResolverController {

    /**
     * HandlerMethodArgumentResolver
     * - 컨트롤러 메소드에 특정 파라미터가 있으면 공통된 로직을 이용하여 처리 할 수 있도록 도와줌
     * - @RequestParam, @PathVariable, @ModelAttribute 모두
     *   HandlerMethodArgumentResolver로 적용된 것
     */

    /**
     * Requester requester를 인자로 받았기 때문에
     * RequesterHandlerMethodArgumentResolver가 실행된다.
     */
    @GetMapping("/requesterHandlerMethodArgumentResolver")
    public Member getMember(Requester requester,
                            Member member,
                            MemberDto memberDto){
        System.out.println(requester);
        return new Member("신건영", 10, Role.ADMIN, ClassType.A);
    }

    @PostMapping("/requesterHandlerMethodArgumentResolver")
    public ResponseEntity<MemberDto> addMember(@RequestBody MemberDto memberDto,
                                    Requester requester){
        System.out.println(requester);
        return ResponseEntity.ok(memberDto);
    }

    /**
     * Pageable pageable을 인자로 받았기 때문에
     * PageableHandlerMethodArgumentResolver가 실행된다.
     */
    @GetMapping("/pageableHandlerMethodArgumentResolver")
    public List<Member> getMembers(Pageable pageable){
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getPageSize());
        return List.of(new Member("신건영", 20, Role.ADMIN, ClassType.A));
    }
}
