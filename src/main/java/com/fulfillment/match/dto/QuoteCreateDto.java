package com.fulfillment.match.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteCreateDto {

    // 견적 대상 요청 ID
    @NotNull(message = "견적 요청을 선택해주세요.")
    private Long shippingRequestId;

    // 견적 제출 업체 ID
    @NotNull(message = "3PL 업체를 선택해주세요.")
    private Long fulfillmentCompanyId;

    // 월 예상 비용
    @NotNull(message = "월 예상 비용을 입력해주세요.")
    @PositiveOrZero(message = "월 예상 비용은 0원 이상이어야 합니다.")
    private Integer monthlyFee;

    // 초기 세팅 비용
    @PositiveOrZero(message = "초기 세팅 비용은 0원 이상이어야 합니다.")
    private Integer setupFee;

    // 보관 비용
    @PositiveOrZero(message = "보관 비용은 0원 이상이어야 합니다.")
    private Integer storageFee;

    // 출고 비용
    @PositiveOrZero(message = "출고 비용은 0원 이상이어야 합니다.")
    private Integer shippingFee;

    // 견적 제안 내용 / 비고
    private String message;
}