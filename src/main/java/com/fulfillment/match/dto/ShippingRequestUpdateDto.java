package com.fulfillment.match.dto;

import com.fulfillment.match.domain.CurrentLogisticsMethod;
import com.fulfillment.match.domain.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingRequestUpdateDto {

    @NotNull(message = "상품 카테고리를 선택해주세요.")
    private ProductCategory productCategory;

    @NotNull(message = "월 주문량을 입력해주세요.")
    @Positive(message = "월 주문량은 1건 이상이어야 합니다.")
    private Integer monthlyOrders;

    @NotNull(message = "SKU 수를 입력해주세요.")
    @Positive(message = "SKU 수는 1개 이상이어야 합니다.")
    private Integer skuCount;

    @NotBlank(message = "희망 지역을 입력해주세요.")
    private String desiredRegion;

    private Boolean coldStorageRequired;

    private Boolean returnInspectionRequired;

    private Boolean specialPackingRequired;

    @NotNull(message = "현재 물류 방식을 선택해주세요.")
    private CurrentLogisticsMethod currentLogisticsMethod;

    private String description;
}