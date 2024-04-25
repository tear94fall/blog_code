package com.example.demo.member.controller;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/member")
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        MemberDto saveMember = memberService.createMember(memberDto);
        return ResponseEntity.ok(saveMember);
    }

    @GetMapping("/api/v1/member/{email}")
    public ResponseEntity<MemberDto> searchMember(@PathVariable("email") String email) {
        MemberDto memberDto = memberService.searchMemberByEmail(email);
        return ResponseEntity.ok(memberDto);
    }
}
