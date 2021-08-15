package com.with.first.dto;

import com.with.first.entity.Member;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
@Log4j2
public class MemberDTO extends User {

    private String id;

    private String password;

    private String name;

    private String lab;

    private String info;

    private Map<String, Object> attr;

    public MemberDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = username;
        this.password = password;
    }

    public MemberDTO(String username, String password,
                     Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(username,password,authorities);
        this.attr = attr;
    }

    public Member toEntity(MemberDTO memberDTO){

        Member member = Member.builder()
                .id(memberDTO.getId())
                .password(memberDTO.getPassword())
                .name(memberDTO.getName())
                .lab(memberDTO.getLab())
                .info(memberDTO.getInfo())
                .build();

        return member;
    }
}