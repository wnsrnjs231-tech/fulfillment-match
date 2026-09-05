package com.fulfillment.match.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record QuoteUpdateDto(
        @PositiveOrZero Integer monthlyFee,
        @PositiveOrZero Integer setupFee,
        @PositiveOrZero Integer storageFee,
        @PositiveOrZero Integer shippingFee,
        String message
) {
}