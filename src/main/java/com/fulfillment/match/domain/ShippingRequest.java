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

    @Builder
    private ShippingRequest(
            ProductCategory productCategory,
            Integer monthlyOrders,
            Boolean coldStorageRequired,
            Integer skuCount,
            String desiredRegion,
            String description,
            Boolean returnInspectionRequired,
            Boolean specialPackingRequired,
            CurrentLogisticsMethod currentLogisticsMethod
    ) {
        this.productCategory = productCategory;
        this.monthlyOrders = monthlyOrders;
        this.coldStorageRequired = coldStorageRequired;
        this.skuCount = skuCount;
        this.desiredRegion = desiredRegion;
        this.description = description;
        this.returnInspectionRequired = returnInspectionRequired;
        this.specialPackingRequired = specialPackingRequired;
        this.currentLogisticsMethod = currentLogisticsMethod;
    }

    public void update(
            ProductCategory productCategory,
            Integer monthlyOrders,
            Boolean coldStorageRequired,
            Integer skuCount,
            String desiredRegion,
            String description,
            Boolean returnInspectionRequired,
            Boolean specialPackingRequired,
            CurrentLogisticsMethod currentLogisticsMethod
    ) {
        this.productCategory = productCategory;
        this.monthlyOrders = monthlyOrders;
        this.coldStorageRequired = coldStorageRequired;
        this.skuCount = skuCount;
        this.desiredRegion = desiredRegion;
        this.description = description;
        this.returnInspectionRequired = returnInspectionRequired;
        this.specialPackingRequired = specialPackingRequired;
        this.currentLogisticsMethod = currentLogisticsMethod;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}