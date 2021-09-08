package com.with.second.repository;

import com.with.second.entity.BucketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<BucketEntity,Long > {
}
