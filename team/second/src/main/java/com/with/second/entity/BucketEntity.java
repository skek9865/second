package com.with.second.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"bookEntity", "memberEntity"})
public class BucketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity bookEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;
}