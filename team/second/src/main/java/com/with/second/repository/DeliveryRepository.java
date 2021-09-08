package com.with.second.repository;

import com.with.second.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
}
