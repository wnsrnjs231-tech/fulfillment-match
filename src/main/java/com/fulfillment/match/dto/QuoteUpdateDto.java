package com.fulfillment.match.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteUpdateDto {

    @NotNull(message = "월 예상 비용을 입력해주세요.")
    @PositiveOrZero(message = "월 예상 비용은 0원 이상이어야 합니다.")
    private Integer monthlyFee;

    @PositiveOrZero(message = "초기 세팅 비용은 0원 이상이어야 합니다.")
    private Integer setupFee;

    @PositiveOrZero(message = "보관 비용은 0원 이상이어야 합니다.")
    private Integer storageFee;

    @PositiveOrZero(message = "출고 비용은 0원 이상이어야 합니다.")
    private Integer shippingFee;

    private String message;
}