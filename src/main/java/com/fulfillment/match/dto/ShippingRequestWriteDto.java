package com.fulfillment.match.dto;

import com.fulfillment.match.domain.CurrentLogisticsMethod;
import com.fulfillment.match.domain.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ShippingRequestWriteDto(

        @NotNull
        ProductCategory productCategory,

        @NotNull
        @Positive
        Integer monthlyOrders,

        @NotNull
        @Positive
        Integer skuCount,

        @NotBlank
        String desiredRegion,

        Boolean coldStorageRequired,

        Boolean returnInspectionRequired,

        Boolean specialPackingRequired,

        @NotNull
        CurrentLogisticsMethod currentLogisticsMethod,

        String description
) {
}