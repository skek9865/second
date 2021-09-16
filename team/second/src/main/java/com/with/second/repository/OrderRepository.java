package com.with.second.repository;

import com.with.second.entity.OrderEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o,o.bookEntity.name,o.bookEntity.book_imgEntity.ino from OrderEntity o where o.memberEntity.id = :id")
    List<Object[]> getList(@Param("id") String id, Sort sort);
}
