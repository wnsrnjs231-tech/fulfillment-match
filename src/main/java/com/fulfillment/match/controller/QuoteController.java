package com.fulfillment.match.controller;

import com.fulfillment.match.domain.FulfillmentCompany;
import com.fulfillment.match.domain.Quote;
import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.QuoteCreateDto;
import com.fulfillment.match.dto.QuoteUpdateDto;
import com.fulfillment.match.service.FulfillmentCompanyService;
import com.fulfillment.match.service.QuoteService;
import com.fulfillment.match.service.ShippingRequestService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuoteController {

    private final QuoteService quoteService;
    private final ShippingRequestService shippingRequestService;
    private final FulfillmentCompanyService fulfillmentCompanyService;

    public QuoteController(
            QuoteService quoteService,
            ShippingRequestService shippingRequestService,
            FulfillmentCompanyService fulfillmentCompanyService
    ) {
        this.quoteService = quoteService;
        this.shippingRequestService = shippingRequestService;
        this.fulfillmentCompanyService = fulfillmentCompanyService;
    }

    @GetMapping("/quotes/new")
    public String newQuoteForm(
            @RequestParam(required = false) Long shippingRequestId,
            @RequestParam(required = false) Long fulfillmentCompanyId,
            Model model
    ) {
        QuoteCreateDto quoteDto = new QuoteCreateDto();

        quoteDto.setShippingRequestId(shippingRequestId);
        quoteDto.setFulfillmentCompanyId(fulfillmentCompanyId);

        model.addAttribute("quoteDto", quoteDto);
        model.addAttribute(
                "requests",
                shippingRequestService.getRequests()
        );
        model.addAttribute(
                "companies",
                fulfillmentCompanyService.getCompanies()
        );

        return "quotes/new";
    }

    @GetMapping("/quotes/{id}/success")
    public String success(@PathVariable Long id, Model model) {
        model.addAttribute("quoteId", id);
        return "quotes/success";
    }

    @GetMapping("/quotes")
    public String quoteList(Model model) {

        List<Quote> quotes =
                quoteService.getQuotes();

        model.addAttribute("quotes", quotes);

        return "quotes/list";
    }

    @GetMapping("/quotes/{id}")
    public String quoteDetail(
            @PathVariable Long id,
            Model model
    ) {
        Quote quote = quoteService.getQuote(id);

        model.addAttribute("quote", quote);

        return "quotes/detail";
    }

    @GetMapping("/quotes/{id}/edit")
    public String editQuoteForm(
            @PathVariable Long id,
            Model model
    ) {
        Quote quote = quoteService.getQuote(id);

        QuoteUpdateDto updateDto = new QuoteUpdateDto();

        updateDto.setMonthlyFee(quote.getMonthlyFee());
        updateDto.setSetupFee(quote.getSetupFee());
        updateDto.setStorageFee(quote.getStorageFee());
        updateDto.setShippingFee(quote.getShippingFee());
        updateDto.setMessage(quote.getMessage());

        model.addAttribute("quoteId", id);
        model.addAttribute("updateDto", updateDto);

        return "quotes/edit";
    }

    @PostMapping("/quotes")
    public String createQuote(
            @Valid @ModelAttribute("quoteDto") QuoteCreateDto quoteDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(
                    "requests",
                    shippingRequestService.getRequests()
            );

            model.addAttribute(
                    "companies",
                    fulfillmentCompanyService.getCompanies()
            );

            return "quotes/new";
        }

        Quote savedQuote =
                quoteService.createQuote(quoteDto);

        return "redirect:/quotes/" + savedQuote.getId() + "/success";
    }

    @PostMapping("/quotes/{id}/edit")
    public String updateQuote(
            @PathVariable Long id,
            @Valid @ModelAttribute("updateDto") QuoteUpdateDto updateDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("quoteId", id);
            return "quotes/edit";
        }

        quoteService.updateQuote(id, updateDto);

        return "redirect:/quotes/" + id;
    }

    @PostMapping("/quotes/{id}/delete")
    public String deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);

        return "redirect:/quotes";
    }
}