package com.with.second.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "bookEntity")
public class Book_ImgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String iname;

    private String uuid;

    @OneToOne(fetch = FetchType.LAZY)
    private BookEntity bookEntity;
}
