package com.fulfillment.match.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FulfillmentCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 업체명
    private String companyName;

    // 사업자등록번호
    private String businessNumber;

    // 담당자명
    private String contactName;

    // 연락처
    private String contactPhone;

    // 이메일
    private String contactEmail;

    // 업체 주소
    private String address;

    // 서비스 가능 지역
    private String serviceRegion;

    // 냉장/냉동 가능 여부
    private Boolean coldStorageAvailable;

    // 반품 검수 가능 여부
    private Boolean returnInspectionAvailable;

    // 특수 포장 가능 여부
    private Boolean specialPackingAvailable;
}