package com.with.first.service;

import com.with.first.dto.MemberDTO;
import com.with.first.entity.Member;
import com.with.first.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberDetailSerivce implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public String SignUp(MemberDTO memberDTO){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        return memberRepository.save(memberDTO.toEntity(memberDTO)).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("username : " + username);

        Optional<Member> result = memberRepository.findByMe(username);

        log.info(result);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check ID");
        }

        Member member = result.get();

        log.info("member : " + member);

        MemberDTO memberDTO = new MemberDTO(
                member.getId(),
                member.getPassword(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toList())
        );

        memberDTO.setName(member.getName());

        return memberDTO;
    }
}
