package com.fulfillment.match.service;

import com.fulfillment.match.domain.FulfillmentCompany;
import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.FulfillmentCompanyWriteDto;
import com.fulfillment.match.dto.MatchingResultDto;
import com.fulfillment.match.exception.FulfillmentCompanyNotFoundException;
import com.fulfillment.match.repository.FulfillmentCompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class FulfillmentCompanyService {

    private final FulfillmentCompanyRepository fulfillmentCompanyRepository;

    public FulfillmentCompanyService(FulfillmentCompanyRepository fulfillmentCompanyRepository) {
        this.fulfillmentCompanyRepository = fulfillmentCompanyRepository;
    }

    public FulfillmentCompany createCompany(FulfillmentCompanyWriteDto dto) {
        FulfillmentCompany company = FulfillmentCompany.builder()
                .companyName(dto.companyName())
                .businessNumber(dto.businessNumber())
                .contactName(dto.contactName())
                .contactPhone(dto.contactPhone())
                .contactEmail(dto.contactEmail())
                .address(dto.address())
                .serviceRegion(dto.serviceRegion())
                .coldStorageAvailable(dto.coldStorageAvailable())
                .returnInspectionAvailable(dto.returnInspectionAvailable())
                .specialPackingAvailable(dto.specialPackingAvailable())
                .build();

        return fulfillmentCompanyRepository.save(company);
    }

    @Transactional(readOnly = true)
    public List<FulfillmentCompany> getCompanies() {
        return fulfillmentCompanyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public FulfillmentCompany getCompany(Long id) {
        return fulfillmentCompanyRepository.findById(id)
                .orElseThrow(() -> new FulfillmentCompanyNotFoundException(id));
    }

    public FulfillmentCompany updateCompany(Long id, FulfillmentCompanyWriteDto dto) {
        FulfillmentCompany company = getCompany(id);

        company.update(
                dto.companyName(),
                dto.businessNumber(),
                dto.contactName(),
                dto.contactPhone(),
                dto.contactEmail(),
                dto.address(),
                dto.serviceRegion(),
                dto.coldStorageAvailable(),
                dto.returnInspectionAvailable(),
                dto.specialPackingAvailable()
        );

        return company;
    }

    public void deleteCompany(Long id) {
        FulfillmentCompany company = getCompany(id);
        fulfillmentCompanyRepository.delete(company);
    }

    @Transactional(readOnly = true)
    public List<MatchingResultDto> findMatchingCompanies(ShippingRequest request) {
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
                                && company.getServiceRegion().contains(request.getDesiredRegion())
                )
                .map(company ->
                        new MatchingResultDto(
                                company,
                                calculateMatchScore(request, company)
                        )
                )
                .sorted(
                        Comparator.comparingInt(MatchingResultDto::score)
                                .reversed()
                )
                .toList();
    }

    private int calculateMatchScore(
            ShippingRequest request,
            FulfillmentCompany company
    ) {
        int score = 0;

        if (company.getServiceRegion() != null
                && request.getDesiredRegion() != null
                && company.getServiceRegion().contains(request.getDesiredRegion())) {
            score += 40;
        }

        if (Boolean.TRUE.equals(request.getColdStorageRequired())
                && Boolean.TRUE.equals(company.getColdStorageAvailable())) {
            score += 20;
        }

        if (Boolean.TRUE.equals(request.getReturnInspectionRequired())
                && Boolean.TRUE.equals(company.getReturnInspectionAvailable())) {
            score += 20;
        }

        if (Boolean.TRUE.equals(request.getSpecialPackingRequired())
                && Boolean.TRUE.equals(company.getSpecialPackingAvailable())) {
            score += 20;
        }

        return score;
    }
}