package com.fulfillment.match.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ShippingRequest shippingRequest;

    @ManyToOne
    private FulfillmentCompany fulfillmentCompany;

    private Integer monthlyFee;
    private Integer setupFee;
    private Integer storageFee;
    private Integer shippingFee;

    @Column(length = 2000)
    private String message;

    private LocalDateTime createdAt;

    @Builder
    private Quote(
            ShippingRequest shippingRequest,
            FulfillmentCompany fulfillmentCompany,
            Integer monthlyFee,
            Integer setupFee,
            Integer storageFee,
            Integer shippingFee,
            String message
    ) {
        this.shippingRequest = shippingRequest;
        this.fulfillmentCompany = fulfillmentCompany;
        this.monthlyFee = monthlyFee;
        this.setupFee = setupFee;
        this.storageFee = storageFee;
        this.shippingFee = shippingFee;
        this.message = message;
    }

    public void update(
            Integer monthlyFee,
            Integer setupFee,
            Integer storageFee,
            Integer shippingFee,
            String message
    ) {
        this.monthlyFee = monthlyFee;
        this.setupFee = setupFee;
        this.storageFee = storageFee;
        this.shippingFee = shippingFee;
        this.message = message;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}