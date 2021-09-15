package com.with.second.controller;


import com.with.second.config.JwtTokenProvider;
import com.with.second.entity.MemberEntity;
import com.with.second.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    // 회원가입
    @PostMapping("/join")
    public String join(@RequestBody Map<String, String> member) {
        return memberRepository.save(MemberEntity.builder()
                .id(member.get("id"))
                .department(member.get("department"))
                .name(member.get("name"))
                .password(passwordEncoder.encode(member.get("password")))
                .roles(Collections.singletonList("ROLE_MEMBER"))
                .build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> member) {
        MemberEntity memberEntity = memberRepository.findById(member.get("id"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 ID입니다."));

        if(!passwordEncoder.matches(member.get("password"), memberEntity.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(memberEntity.getUsername(), memberEntity.getRoles());
    }

    @GetMapping("/login")
    public void hello(){
    }
}
