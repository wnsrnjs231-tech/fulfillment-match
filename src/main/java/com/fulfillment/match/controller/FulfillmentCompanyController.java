package com.fulfillment.match.controller;

import com.fulfillment.match.domain.FulfillmentCompany;
import com.fulfillment.match.dto.FulfillmentCompanyCreateDto;
import com.fulfillment.match.dto.FulfillmentCompanyUpdateDto;
import com.fulfillment.match.service.FulfillmentCompanyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FulfillmentCompanyController {

    private final FulfillmentCompanyService fulfillmentCompanyService;

    public FulfillmentCompanyController(
            FulfillmentCompanyService fulfillmentCompanyService
    ) {
        this.fulfillmentCompanyService = fulfillmentCompanyService;
    }

    @GetMapping("/companies/new")
    public String newCompanyForm(Model model) {
        model.addAttribute(
                "companyDto",
                new FulfillmentCompanyCreateDto()
        );

        return "companies/new";
    }

    @GetMapping("/companies/{id}/success")
    public String success(@PathVariable Long id, Model model) {
        model.addAttribute("companyId", id);
        return "companies/success";
    }

    @GetMapping("/companies")
    public String companyList(Model model) {
        List<FulfillmentCompany> companies =
                fulfillmentCompanyService.getCompanies();

        model.addAttribute("companies", companies);

        return "companies/list";
    }

    @GetMapping("/companies/{id}")
    public String companyDetail(
            @PathVariable Long id,
            Model model
    ) {
        FulfillmentCompany company =
                fulfillmentCompanyService.getCompany(id);

        model.addAttribute("company", company);

        return "companies/detail";
    }

    @GetMapping("/companies/{id}/edit")
    public String editCompanyForm(
            @PathVariable Long id,
            Model model
    ) {
        FulfillmentCompany company =
                fulfillmentCompanyService.getCompany(id);

        FulfillmentCompanyUpdateDto updateDto =
                new FulfillmentCompanyUpdateDto();

        updateDto.setCompanyName(company.getCompanyName());
        updateDto.setBusinessNumber(company.getBusinessNumber());
        updateDto.setContactName(company.getContactName());
        updateDto.setContactPhone(company.getContactPhone());
        updateDto.setContactEmail(company.getContactEmail());
        updateDto.setAddress(company.getAddress());
        updateDto.setServiceRegion(company.getServiceRegion());
        updateDto.setColdStorageAvailable(company.getColdStorageAvailable());
        updateDto.setReturnInspectionAvailable(company.getReturnInspectionAvailable());
        updateDto.setSpecialPackingAvailable(company.getSpecialPackingAvailable());

        model.addAttribute("companyId", id);
        model.addAttribute("updateDto", updateDto);

        return "companies/edit";
    }

    @PostMapping("/companies")
    public String createCompany(
            @Valid @ModelAttribute("companyDto")
            FulfillmentCompanyCreateDto companyDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "companies/new";
        }

        FulfillmentCompany savedCompany =
                fulfillmentCompanyService.createCompany(companyDto);

        return "redirect:/companies/" + savedCompany.getId() + "/success";
    }

    @PostMapping("/companies/{id}/edit")
    public String updateCompany(
            @PathVariable Long id,
            @Valid @ModelAttribute("updateDto") FulfillmentCompanyUpdateDto updateDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("companyId", id);
            return "companies/edit";
        }

        fulfillmentCompanyService.updateCompany(id, updateDto);

        return "redirect:/companies/" + id;
    }

    @PostMapping("/companies/{id}/delete")
    public String deleteCompany(@PathVariable Long id) {
        fulfillmentCompanyService.deleteCompany(id);

        return "redirect:/companies";
    }
}