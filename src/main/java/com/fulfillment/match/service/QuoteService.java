package com.fulfillment.match.service;

import com.fulfillment.match.domain.FulfillmentCompany;
import com.fulfillment.match.domain.Quote;
import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.QuoteCreateDto;
import com.fulfillment.match.dto.QuoteUpdateDto;
import com.fulfillment.match.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final ShippingRequestService shippingRequestService;
    private final FulfillmentCompanyService fulfillmentCompanyService;

    public QuoteService(
            QuoteRepository quoteRepository,
            ShippingRequestService shippingRequestService,
            FulfillmentCompanyService fulfillmentCompanyService
    ) {
        this.quoteRepository = quoteRepository;
        this.shippingRequestService = shippingRequestService;
        this.fulfillmentCompanyService = fulfillmentCompanyService;
    }

    public Quote createQuote(QuoteCreateDto createDto) {

        ShippingRequest shippingRequest =
                shippingRequestService.getRequest(
                        createDto.getShippingRequestId()
                );

        FulfillmentCompany fulfillmentCompany =
                fulfillmentCompanyService.getCompany(
                        createDto.getFulfillmentCompanyId()
                );

        Quote quote = new Quote();

        quote.setShippingRequest(shippingRequest);
        quote.setFulfillmentCompany(fulfillmentCompany);
        quote.setMonthlyFee(createDto.getMonthlyFee());
        quote.setSetupFee(createDto.getSetupFee());
        quote.setStorageFee(createDto.getStorageFee());
        quote.setShippingFee(createDto.getShippingFee());
        quote.setMessage(createDto.getMessage());

        return quoteRepository.save(quote);
    }

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getQuote(Long id) {
        return quoteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("견적을 찾을 수 없습니다. id=" + id)
                );
    }

    public Quote updateQuote(
            Long id,
            QuoteUpdateDto updateDto
    ) {
        Quote quote = getQuote(id);

        quote.setMonthlyFee(updateDto.getMonthlyFee());
        quote.setSetupFee(updateDto.getSetupFee());
        quote.setStorageFee(updateDto.getStorageFee());
        quote.setShippingFee(updateDto.getShippingFee());
        quote.setMessage(updateDto.getMessage());

        return quoteRepository.save(quote);
    }

    public void deleteQuote(Long id) {
        Quote quote = getQuote(id);
        quoteRepository.delete(quote);
    }
}