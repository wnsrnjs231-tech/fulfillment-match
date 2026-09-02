package com.fulfillment.match.service;

import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.ShippingRequestCreateDto;
import com.fulfillment.match.dto.ShippingRequestUpdateDto;
import com.fulfillment.match.exception.ShippingRequestNotFoundException;
import com.fulfillment.match.repository.ShippingRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingRequestService {

    private final ShippingRequestRepository shippingRequestRepository;

    public ShippingRequestService(ShippingRequestRepository shippingRequestRepository) {
        this.shippingRequestRepository = shippingRequestRepository;
    }

    public ShippingRequest createRequest(ShippingRequestCreateDto requestDto) {

        ShippingRequest shippingRequest = new ShippingRequest();

        shippingRequest.setProductCategory(requestDto.getProductCategory());
        shippingRequest.setMonthlyOrders(requestDto.getMonthlyOrders());
        shippingRequest.setSkuCount(requestDto.getSkuCount());
        shippingRequest.setDesiredRegion(requestDto.getDesiredRegion());
        shippingRequest.setColdStorageRequired(requestDto.getColdStorageRequired());
        shippingRequest.setReturnInspectionRequired(requestDto.getReturnInspectionRequired());
        shippingRequest.setSpecialPackingRequired(requestDto.getSpecialPackingRequired());
        shippingRequest.setCurrentLogisticsMethod(requestDto.getCurrentLogisticsMethod());
        shippingRequest.setDescription(requestDto.getDescription());

        return shippingRequestRepository.save(shippingRequest);
    }

    public ShippingRequest getRequest(Long id) {
        return shippingRequestRepository.findById(id)
                .orElseThrow(() -> new ShippingRequestNotFoundException(id));
    }

    public List<ShippingRequest> getRequests() {
        return shippingRequestRepository.findAll();
    }

    public ShippingRequest updateRequest(
            Long id,
            ShippingRequestUpdateDto updateDto
    ) {
        ShippingRequest shippingRequest = getRequest(id);

        shippingRequest.setProductCategory(updateDto.getProductCategory());
        shippingRequest.setMonthlyOrders(updateDto.getMonthlyOrders());
        shippingRequest.setSkuCount(updateDto.getSkuCount());
        shippingRequest.setDesiredRegion(updateDto.getDesiredRegion());
        shippingRequest.setCurrentLogisticsMethod(updateDto.getCurrentLogisticsMethod());
        shippingRequest.setColdStorageRequired(updateDto.getColdStorageRequired());
        shippingRequest.setReturnInspectionRequired(updateDto.getReturnInspectionRequired());
        shippingRequest.setSpecialPackingRequired(updateDto.getSpecialPackingRequired());
        shippingRequest.setDescription(updateDto.getDescription());

        return shippingRequestRepository.save(shippingRequest);
    }

    public void deleteRequest(Long id) {

        ShippingRequest shippingRequest = getRequest(id);

        shippingRequestRepository.delete(shippingRequest);
    }
}