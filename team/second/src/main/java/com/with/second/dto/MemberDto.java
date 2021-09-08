package com.with.second.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberDto {

    private String id;

    private String password;

    private String name;

    private String department;
}
