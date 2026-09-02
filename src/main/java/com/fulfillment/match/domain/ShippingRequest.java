package com.fulfillment.match.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ShippingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    private Integer monthlyOrders;

    private Boolean coldStorageRequired;

    private Integer skuCount;

    private String desiredRegion;

    @Column(length = 2000)
    private String description;

    private Boolean returnInspectionRequired;
    private Boolean specialPackingRequired;

    @Enumerated(EnumType.STRING)
    private CurrentLogisticsMethod currentLogisticsMethod;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}