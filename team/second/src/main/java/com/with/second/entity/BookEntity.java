package com.with.second.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "book_imgEntity")
public class BookEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String name;

    private int price;

    private String department;

    private boolean isNew;

    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    private Book_ImgEntity book_imgEntity;
}
