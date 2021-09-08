package com.with.second.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BucketDto {

    private Long sno;

    private Long bno;   //Book bno

    private String name;   //Book name

    private int price;

    private String id;  //Member id
}
