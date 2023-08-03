package com.example.demo.member.controller;

import com.example.demo.member.dto.MemberDto;
import com.example.demo.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
        @AllArgsConstructor
        public class MemberController {

            private final MemberService memberService;

            @GetMapping("member/{id}")
            public ResponseEntity<MemberDto> findMember(@PathVariable Long id) {
                return ResponseEntity.ok().body(memberService.findMemberById(id));
            }

            @PostMapping("member")
            public ResponseEntity<MemberDto> registerMember(@RequestBody MemberDto memberDto) {
                return ResponseEntity.ok().body(memberService.createMember(memberDto));
    }
}
