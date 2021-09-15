package com.with.second.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class BucketDto {

    private Long sno;

    private Long bno;   //Book bno

    private String bookName;   //Book name

    private int price;

    private String userId;  //Member id
}
