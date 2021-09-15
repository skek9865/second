package com.with.second.service;

import com.with.second.dto.OrderDto;
import com.with.second.entity.BookEntity;
import com.with.second.entity.MemberEntity;
import com.with.second.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    void register(OrderDto dto);

    List<OrderDto> getList(String id);

    void update(Long ono, String status);

    void remove(Long ono);

    default OrderDto entityToDTO(OrderEntity entity, String bookName){

        BookEntity bookEntity = BookEntity.builder()
                .bno(entity.getBookEntity().getBno())
                .name(bookName)
                .build();

        MemberEntity memberEntity = MemberEntity.builder().id(entity.getMemberEntity().getId()).build();

        OrderDto dto = OrderDto.builder()
                .ono(entity.getOno())
                .bno(bookEntity.getBno())
                .bookName(bookEntity.getName())
                .userId(memberEntity.getId())
                .orderDate(entity.getRegdate())
                .status(entity.getStatus())
                .build();

        return dto;
    }

    default OrderEntity dtoToEntity(OrderDto dto){

        BookEntity bookEntity = BookEntity.builder()
                .bno(dto.getBno())
                .name(dto.getBookName())
                .build();

        MemberEntity memberEntity = MemberEntity.builder().id(dto.getUserId()).build();

        OrderEntity orderEntity = OrderEntity.builder()
                .bookEntity(bookEntity)
                .memberEntity(memberEntity)
                .status(dto.getStatus())
                .build();

        return orderEntity;
    }
}
