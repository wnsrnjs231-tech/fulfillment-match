package com.fulfillment.match.repository;

import com.fulfillment.match.domain.FulfillmentCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FulfillmentCompanyRepository
        extends JpaRepository<FulfillmentCompany, Long> {
}