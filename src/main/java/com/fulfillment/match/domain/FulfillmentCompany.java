package com.fulfillment.match.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FulfillmentCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String businessNumber;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String address;
    private String serviceRegion;
    private Boolean coldStorageAvailable;
    private Boolean returnInspectionAvailable;
    private Boolean specialPackingAvailable;

    @Builder
    private FulfillmentCompany(
            String companyName,
            String businessNumber,
            String contactName,
            String contactPhone,
            String contactEmail,
            String address,
            String serviceRegion,
            Boolean coldStorageAvailable,
            Boolean returnInspectionAvailable,
            Boolean specialPackingAvailable
    ) {
        this.companyName = companyName;
        this.businessNumber = businessNumber;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.address = address;
        this.serviceRegion = serviceRegion;
        this.coldStorageAvailable = coldStorageAvailable;
        this.returnInspectionAvailable = returnInspectionAvailable;
        this.specialPackingAvailable = specialPackingAvailable;
    }

    public void update(
            String companyName,
            String businessNumber,
            String contactName,
            String contactPhone,
            String contactEmail,
            String address,
            String serviceRegion,
            Boolean coldStorageAvailable,
            Boolean returnInspectionAvailable,
            Boolean specialPackingAvailable
    ) {
        this.companyName = companyName;
        this.businessNumber = businessNumber;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.address = address;
        this.serviceRegion = serviceRegion;
        this.coldStorageAvailable = coldStorageAvailable;
        this.returnInspectionAvailable = returnInspectionAvailable;
        this.specialPackingAvailable = specialPackingAvailable;
    }
}