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

    private String bookName;   //Book name

    private int price;  //Book price

    private String userId;  //Member id
}
