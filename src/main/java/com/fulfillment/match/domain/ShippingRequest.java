package com.fulfillment.match.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "상품 카테고리를 선택해주세요.")
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @NotNull(message = "월 주문량을 입력해주세요.")
    @Positive(message = "월 주문량은 1건 이상이어야 합니다.")
    private Integer monthlyOrders;

    private Boolean coldStorageRequired;

    @NotNull(message = "SKU 수를 입력해주세요.")
    @Positive(message = "SKU 수는 1개 이상이어야 합니다.")
    private Integer skuCount;

    @NotBlank(message = "희망 지역을 입력해주세요.")
    private String desiredRegion;

    @Column(length = 2000)
    private String description;

    private Boolean returnInspectionRequired;
    private Boolean specialPackingRequired;

    @NotNull(message = "현재 물류 방식을 선택해주세요.")
    @Enumerated(EnumType.STRING)
    private CurrentLogisticsMethod currentLogisticsMethod;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}