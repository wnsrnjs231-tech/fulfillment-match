package com.fulfillment.match.controller;

import com.fulfillment.match.domain.FulfillmentCompany;
import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.MatchingResultDto;
import com.fulfillment.match.dto.ShippingRequestCreateDto;
import com.fulfillment.match.dto.ShippingRequestUpdateDto;
import com.fulfillment.match.service.FulfillmentCompanyService;
import com.fulfillment.match.service.ShippingRequestService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
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


    @GetMapping("/requests/new")
    public String newRequestForm(Model model) {
        model.addAttribute("requestDto", new ShippingRequestCreateDto());
        return "requests/new";
    }

    @GetMapping("/requests/{id}/success")
    public String success(@PathVariable Long id, Model model) {
        model.addAttribute("requestId", id);
        return "requests/success";
    }

    @GetMapping("/requests/{id}")
    public String requestDetail(@PathVariable Long id, Model model) {

        ShippingRequest shippingRequest =
                shippingRequestService.getRequest(id);

        model.addAttribute("shippingRequest", shippingRequest);

        return "requests/detail";
    }

    @GetMapping("/requests")
    public String requestList(Model model) {

        List<ShippingRequest> requests =
                shippingRequestService.getRequests();

        System.out.println("요청 개수 = " + requests.size());

        model.addAttribute("requests", requests);

        return "requests/list";
    }

    @GetMapping("/requests/{id}/edit")
    public String editRequestForm(@PathVariable Long id, Model model) {

        ShippingRequest shippingRequest =
                shippingRequestService.getRequest(id);

        ShippingRequestUpdateDto updateDto =
                new ShippingRequestUpdateDto();

        updateDto.setProductCategory(shippingRequest.getProductCategory());
        updateDto.setMonthlyOrders(shippingRequest.getMonthlyOrders());
        updateDto.setSkuCount(shippingRequest.getSkuCount());
        updateDto.setDesiredRegion(shippingRequest.getDesiredRegion());
        updateDto.setColdStorageRequired(shippingRequest.getColdStorageRequired());
        updateDto.setReturnInspectionRequired(shippingRequest.getReturnInspectionRequired());
        updateDto.setSpecialPackingRequired(shippingRequest.getSpecialPackingRequired());
        updateDto.setCurrentLogisticsMethod(shippingRequest.getCurrentLogisticsMethod());
        updateDto.setDescription(shippingRequest.getDescription());

        model.addAttribute("requestId", id);
        model.addAttribute("updateDto", updateDto);

        return "requests/edit";
    }

    @GetMapping("/requests/{id}/matches")
    public String matchingCompanies(
            @PathVariable Long id,
            Model model
    ) {
        ShippingRequest request =
                shippingRequestService.getRequest(id);

        List<MatchingResultDto> matches =
                fulfillmentCompanyService.findMatchingCompanies(request);

        model.addAttribute("shippingRequest", request);
        model.addAttribute("matches", matches);

        return "requests/matches";
    }

    @PostMapping("/requests")
    public String createRequest(
            @Valid @ModelAttribute("requestDto") ShippingRequestCreateDto requestDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "requests/new";
        }

        ShippingRequest savedRequest =
                shippingRequestService.createRequest(requestDto);

        return "redirect:/requests/" + savedRequest.getId() + "/success";
    }

    @PostMapping("/requests/{id}/delete")
    public String deleteRequest(@PathVariable Long id) {

        shippingRequestService.deleteRequest(id);

        return "redirect:/requests";
    }

    @PostMapping("/requests/{id}/edit")
    public String updateRequest(
            @PathVariable Long id,
            @Valid @ModelAttribute("updateDto") ShippingRequestUpdateDto updateDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("requestId", id);
            return "requests/edit";
        }

        shippingRequestService.updateRequest(id, updateDto);
        return "redirect:/requests/" + id;
    }
}