package com.with.second.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"bookEntity","memberEntity"})
public class OrderEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookEntity bookEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;

    private String status;

    public void changeStatus(String status) {
        this.status = status;
    }
}
