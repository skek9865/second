package com.with.second.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "orderEntity")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dno;

    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;
}
