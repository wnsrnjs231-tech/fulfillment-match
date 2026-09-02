package com.fulfillment.match.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FulfillmentCompanyCreateDto {

    @NotBlank(message = "업체명을 입력해주세요.")
    private String companyName;

    @NotBlank(message = "사업자등록번호를 입력해주세요.")
    private String businessNumber;

    @NotBlank(message = "담당자명을 입력해주세요.")
    private String contactName;

    @NotBlank(message = "연락처를 입력해주세요.")
    private String contactPhone;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식으로 입력해주세요.")
    private String contactEmail;

    @NotBlank(message = "업체 주소를 입력해주세요.")
    private String address;

    @NotBlank(message = "서비스 가능 지역을 입력해주세요.")
    private String serviceRegion;

    private Boolean coldStorageAvailable;

    private Boolean returnInspectionAvailable;

    private Boolean specialPackingAvailable;
}