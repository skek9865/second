package com.with.second.dto;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookDto {

    private Long bno;

    private String name;

    private int price;

    private String department;

    private boolean isNew;

    private String status;

    private LocalDateTime regDate;

    private Book_ImgDto book_img;
}
