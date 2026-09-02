package com.fulfillment.match.repository;

import com.fulfillment.match.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository
        extends JpaRepository<Quote, Long> {
}