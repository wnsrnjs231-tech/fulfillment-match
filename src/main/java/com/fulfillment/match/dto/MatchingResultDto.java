package com.fulfillment.match.dto;

import com.fulfillment.match.domain.FulfillmentCompany;
import lombok.Getter;

@Getter
public class MatchingResultDto {

    // 매칭된 3PL 업체
    private final FulfillmentCompany company;

    // 매칭 점수
    private final int score;

    public MatchingResultDto(
            FulfillmentCompany company,
            int score
    ) {
        this.company = company;
        this.score = score;
    }
}