package com.fulfillment.match.repository;

import com.fulfillment.match.domain.ShippingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRequestRepository
        extends JpaRepository<ShippingRequest, Long> {
}