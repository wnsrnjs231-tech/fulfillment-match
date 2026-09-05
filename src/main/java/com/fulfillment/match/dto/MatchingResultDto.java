package com.fulfillment.match.dto;

import com.fulfillment.match.domain.FulfillmentCompany;

public record MatchingResultDto(
        FulfillmentCompany company,
        int score
) {
}