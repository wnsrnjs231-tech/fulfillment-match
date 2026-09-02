package com.fulfillment.match.service;

import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.ShippingRequestCreateDto;
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

    public ShippingRequest updateRequest(Long id, ShippingRequest updatedRequest) {

        ShippingRequest shippingRequest = getRequest(id);

        shippingRequest.setProductCategory(updatedRequest.getProductCategory());
        shippingRequest.setMonthlyOrders(updatedRequest.getMonthlyOrders());
        shippingRequest.setSkuCount(updatedRequest.getSkuCount());
        shippingRequest.setDesiredRegion(updatedRequest.getDesiredRegion());

        shippingRequest.setCurrentLogisticsMethod(
                updatedRequest.getCurrentLogisticsMethod()
        );

        shippingRequest.setColdStorageRequired(
                updatedRequest.getColdStorageRequired()
        );

        shippingRequest.setReturnInspectionRequired(
                updatedRequest.getReturnInspectionRequired()
        );

        shippingRequest.setSpecialPackingRequired(
                updatedRequest.getSpecialPackingRequired()
        );

        shippingRequest.setDescription(
                updatedRequest.getDescription()
        );

        return shippingRequestRepository.save(shippingRequest);
    }

    public void deleteRequest(Long id) {

        ShippingRequest shippingRequest = getRequest(id);

        shippingRequestRepository.delete(shippingRequest);
    }
}