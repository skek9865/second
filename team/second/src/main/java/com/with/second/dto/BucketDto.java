package com.with.second.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class BucketDto {

    private Long sno;

    private Long bno;   //Book bno

    private String name;   //Book name

    private int price;

    private String id;  //Member id
}
