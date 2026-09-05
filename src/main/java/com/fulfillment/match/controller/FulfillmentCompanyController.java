package com.fulfillment.match.controller;

import com.fulfillment.match.domain.FulfillmentCompany;
import com.fulfillment.match.dto.FulfillmentCompanyWriteDto;
import com.fulfillment.match.service.FulfillmentCompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class FulfillmentCompanyController {

    private final FulfillmentCompanyService fulfillmentCompanyService;

    public FulfillmentCompanyController(FulfillmentCompanyService fulfillmentCompanyService) {
        this.fulfillmentCompanyService = fulfillmentCompanyService;
    }

    @GetMapping
    public List<FulfillmentCompany> getCompanies() {
        return fulfillmentCompanyService.getCompanies();
    }

    @GetMapping("/{id}")
    public FulfillmentCompany getCompany(@PathVariable Long id) {
        return fulfillmentCompanyService.getCompany(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FulfillmentCompany createCompany(
            @Valid @RequestBody FulfillmentCompanyWriteDto dto
    ) {
        return fulfillmentCompanyService.createCompany(dto);
    }

    @PutMapping("/{id}")
    public FulfillmentCompany updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody FulfillmentCompanyWriteDto dto
    ) {
        return fulfillmentCompanyService.updateCompany(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long id) {
        fulfillmentCompanyService.deleteCompany(id);
    }
}