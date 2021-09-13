package com.with.second.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book_ImgDto {

    private Long ino;

    private String iname;

    private String uuid;

    private String path;
}
