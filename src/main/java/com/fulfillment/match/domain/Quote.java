package com.fulfillment.match.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 견적 대상 요청
    @ManyToOne
    private ShippingRequest shippingRequest;

    // 견적을 제출한 3PL 업체
    @ManyToOne
    private FulfillmentCompany fulfillmentCompany;

    // 월 예상 비용
    private Integer monthlyFee;

    // 초기 세팅 비용
    private Integer setupFee;

    // 보관 비용
    private Integer storageFee;

    // 출고 비용
    private Integer shippingFee;

    // 견적 제안 내용 / 비고
    @Column(length = 2000)
    private String message;

    // 견적 생성 시간
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}