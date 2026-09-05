package com.fulfillment.match.service;

import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.ShippingRequestWriteDto;
import com.fulfillment.match.exception.ShippingRequestNotFoundException;
import com.fulfillment.match.repository.ShippingRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShippingRequestService {

    private final ShippingRequestRepository shippingRequestRepository;

    public ShippingRequestService(ShippingRequestRepository shippingRequestRepository) {
        this.shippingRequestRepository = shippingRequestRepository;
    }

    public ShippingRequest createRequest(ShippingRequestWriteDto dto) {
        ShippingRequest shippingRequest = ShippingRequest.builder()
                .productCategory(dto.productCategory())
                .monthlyOrders(dto.monthlyOrders())
                .skuCount(dto.skuCount())
                .desiredRegion(dto.desiredRegion())
                .coldStorageRequired(dto.coldStorageRequired())
                .returnInspectionRequired(dto.returnInspectionRequired())
                .specialPackingRequired(dto.specialPackingRequired())
                .currentLogisticsMethod(dto.currentLogisticsMethod())
                .description(dto.description())
                .build();

        return shippingRequestRepository.save(shippingRequest);
    }

    @Transactional(readOnly = true)
    public ShippingRequest getRequest(Long id) {
        return shippingRequestRepository.findById(id)
                .orElseThrow(() -> new ShippingRequestNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<ShippingRequest> getRequests() {
        return shippingRequestRepository.findAll();
    }

    public ShippingRequest updateRequest(Long id, ShippingRequestWriteDto dto) {
        ShippingRequest shippingRequest = getRequest(id);

        shippingRequest.update(
                dto.productCategory(),
                dto.monthlyOrders(),
                dto.coldStorageRequired(),
                dto.skuCount(),
                dto.desiredRegion(),
                dto.description(),
                dto.returnInspectionRequired(),
                dto.specialPackingRequired(),
                dto.currentLogisticsMethod()
        );

        return shippingRequest;
    }

    public void deleteRequest(Long id) {
        ShippingRequest shippingRequest = getRequest(id);
        shippingRequestRepository.delete(shippingRequest);
    }
}