package com.fulfillment.match.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record QuoteCreateDto(
        @NotNull Long shippingRequestId,
        @NotNull Long fulfillmentCompanyId,
        @PositiveOrZero Integer monthlyFee,
        @PositiveOrZero Integer setupFee,
        @PositiveOrZero Integer storageFee,
        @PositiveOrZero Integer shippingFee,
        String message
) {
}