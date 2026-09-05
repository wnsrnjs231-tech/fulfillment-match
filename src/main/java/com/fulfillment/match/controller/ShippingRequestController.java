package com.fulfillment.match.controller;

import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.MatchingResultDto;
import com.fulfillment.match.dto.ShippingRequestWriteDto;
import com.fulfillment.match.service.FulfillmentCompanyService;
import com.fulfillment.match.service.ShippingRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ShippingRequestController {

    private final ShippingRequestService shippingRequestService;
    private final FulfillmentCompanyService fulfillmentCompanyService;

    public ShippingRequestController(
            ShippingRequestService shippingRequestService,
            FulfillmentCompanyService fulfillmentCompanyService
    ) {
        this.shippingRequestService = shippingRequestService;
        this.fulfillmentCompanyService = fulfillmentCompanyService;
    }

    @GetMapping
    public List<ShippingRequest> getRequests() {
        return shippingRequestService.getRequests();
    }

    @GetMapping("/{id}")
    public ShippingRequest getRequest(@PathVariable Long id) {
        return shippingRequestService.getRequest(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShippingRequest createRequest(
            @Valid @RequestBody ShippingRequestWriteDto dto
    ) {
        return shippingRequestService.createRequest(dto);
    }

    @PutMapping("/{id}")
    public ShippingRequest updateRequest(
            @PathVariable Long id,
            @Valid @RequestBody ShippingRequestWriteDto dto
    ) {
        return shippingRequestService.updateRequest(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequest(@PathVariable Long id) {
        shippingRequestService.deleteRequest(id);
    }

    @GetMapping("/{id}/matches")
    public List<MatchingResultDto> getMatches(@PathVariable Long id) {
        ShippingRequest request = shippingRequestService.getRequest(id);
        return fulfillmentCompanyService.findMatchingCompanies(request);
    }
}