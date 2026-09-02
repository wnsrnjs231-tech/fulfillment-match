package com.fulfillment.match.service;

import com.fulfillment.match.domain.FulfillmentCompany;
import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.FulfillmentCompanyCreateDto;
import com.fulfillment.match.dto.FulfillmentCompanyUpdateDto;
import com.fulfillment.match.dto.MatchingResultDto;
import com.fulfillment.match.repository.FulfillmentCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class FulfillmentCompanyService {

    private final FulfillmentCompanyRepository fulfillmentCompanyRepository;

    public FulfillmentCompanyService(
            FulfillmentCompanyRepository fulfillmentCompanyRepository
    ) {
        this.fulfillmentCompanyRepository = fulfillmentCompanyRepository;
    }

    public FulfillmentCompany createCompany(
            FulfillmentCompanyCreateDto createDto
    ) {
        FulfillmentCompany company = new FulfillmentCompany();

        company.setCompanyName(createDto.getCompanyName());
        company.setBusinessNumber(createDto.getBusinessNumber());
        company.setContactName(createDto.getContactName());
        company.setContactPhone(createDto.getContactPhone());
        company.setContactEmail(createDto.getContactEmail());
        company.setAddress(createDto.getAddress());
        company.setServiceRegion(createDto.getServiceRegion());
        company.setColdStorageAvailable(createDto.getColdStorageAvailable());
        company.setReturnInspectionAvailable(createDto.getReturnInspectionAvailable());
        company.setSpecialPackingAvailable(createDto.getSpecialPackingAvailable());

        return fulfillmentCompanyRepository.save(company);
    }

    public List<FulfillmentCompany> getCompanies() {
        return fulfillmentCompanyRepository.findAll();
    }

    public FulfillmentCompany getCompany(Long id) {
        return fulfillmentCompanyRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("업체를 찾을 수 없습니다. id=" + id)
                );
    }

    public FulfillmentCompany updateCompany(
            Long id,
            FulfillmentCompanyUpdateDto updateDto
    ) {
        FulfillmentCompany company = getCompany(id);

        company.setCompanyName(updateDto.getCompanyName());
        company.setBusinessNumber(updateDto.getBusinessNumber());
        company.setContactName(updateDto.getContactName());
        company.setContactPhone(updateDto.getContactPhone());
        company.setContactEmail(updateDto.getContactEmail());
        company.setAddress(updateDto.getAddress());
        company.setServiceRegion(updateDto.getServiceRegion());
        company.setColdStorageAvailable(updateDto.getColdStorageAvailable());
        company.setReturnInspectionAvailable(updateDto.getReturnInspectionAvailable());
        company.setSpecialPackingAvailable(updateDto.getSpecialPackingAvailable());

        return fulfillmentCompanyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        FulfillmentCompany company = getCompany(id);
        fulfillmentCompanyRepository.delete(company);
    }

    public List<MatchingResultDto> findMatchingCompanies(
            ShippingRequest request
    ) {
        return fulfillmentCompanyRepository.findAll()
                .stream()
                .filter(company ->
                        !Boolean.TRUE.equals(request.getColdStorageRequired())
                                || Boolean.TRUE.equals(company.getColdStorageAvailable())
                )
                .filter(company ->
                        !Boolean.TRUE.equals(request.getReturnInspectionRequired())
                                || Boolean.TRUE.equals(company.getReturnInspectionAvailable())
                )
                .filter(company ->
                        !Boolean.TRUE.equals(request.getSpecialPackingRequired())
                                || Boolean.TRUE.equals(company.getSpecialPackingAvailable())
                )
                .filter(company ->
                        company.getServiceRegion() != null
                                && request.getDesiredRegion() != null
                                && company.getServiceRegion()
                                .contains(request.getDesiredRegion())
                )
                .map(company ->
                        new MatchingResultDto(
                                company,
                                calculateMatchScore(request, company)
                        )
                )
                .sorted(
                        Comparator.comparingInt(MatchingResultDto::getScore)
                                .reversed()
                )
                .toList();
    }

    public int calculateMatchScore(
            ShippingRequest request,
            FulfillmentCompany company
    ) {
        int score = 0;

        // 지역 일치
        if (company.getServiceRegion() != null
                && request.getDesiredRegion() != null
                && company.getServiceRegion().contains(request.getDesiredRegion())) {
            score += 40;
        }

        // 냉장/냉동
        if (Boolean.TRUE.equals(request.getColdStorageRequired())
                && Boolean.TRUE.equals(company.getColdStorageAvailable())) {
            score += 20;
        }

        // 반품 검수
        if (Boolean.TRUE.equals(request.getReturnInspectionRequired())
                && Boolean.TRUE.equals(company.getReturnInspectionAvailable())) {
            score += 20;
        }

        // 특수 포장
        if (Boolean.TRUE.equals(request.getSpecialPackingRequired())
                && Boolean.TRUE.equals(company.getSpecialPackingAvailable())) {
            score += 20;
        }

        return score;
    }
}