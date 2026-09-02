package com.fulfillment.match.controller;

import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.ShippingRequestCreateDto;
import com.fulfillment.match.service.ShippingRequestService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShippingRequestController {

    private final ShippingRequestService shippingRequestService;

    public ShippingRequestController(ShippingRequestService shippingRequestService) {
        this.shippingRequestService = shippingRequestService;
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
    public String editRequestForm(
            @PathVariable Long id,
            Model model
    ) {
        ShippingRequest shippingRequest =
                shippingRequestService.getRequest(id);

        model.addAttribute("shippingRequest", shippingRequest);

        return "requests/edit";
    }

    @PostMapping("/requests")
    public String createRequest(
            @Valid ShippingRequestCreateDto requestDto,
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
}