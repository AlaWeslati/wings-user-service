package com.wings.wingsuserservice.repository;

import com.wings.wingsuserservice.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Boolean existsByEmail(String email);
}
