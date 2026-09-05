package com.fulfillment.match.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FulfillmentCompanyWriteDto(

        @NotBlank
        String companyName,

        @NotBlank
        String businessNumber,

        @NotBlank
        String contactName,

        @NotBlank
        String contactPhone,

        @Email
        String contactEmail,

        String address,

        @NotBlank
        String serviceRegion,

        Boolean coldStorageAvailable,

        Boolean returnInspectionAvailable,

        Boolean specialPackingAvailable
) {
}