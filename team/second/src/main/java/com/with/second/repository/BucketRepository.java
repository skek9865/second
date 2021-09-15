package com.with.second.repository;

import com.with.second.entity.BucketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BucketRepository extends JpaRepository<BucketEntity,Long > {

    @Query("select c, c.bookEntity.name from BucketEntity c where c.memberEntity.id = :id")
    List<Object[]> getList(@Param("id") String id);


}
