package com.fulfillment.match.service;

import com.fulfillment.match.domain.FulfillmentCompany;
import com.fulfillment.match.domain.Quote;
import com.fulfillment.match.domain.ShippingRequest;
import com.fulfillment.match.dto.QuoteCreateDto;
import com.fulfillment.match.dto.QuoteUpdateDto;
import com.fulfillment.match.exception.QuoteNotFoundException;
import com.fulfillment.match.repository.QuoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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

    public Quote createQuote(QuoteCreateDto dto) {
        ShippingRequest shippingRequest = shippingRequestService.getRequest(dto.shippingRequestId());
        FulfillmentCompany fulfillmentCompany =
                fulfillmentCompanyService.getCompany(dto.fulfillmentCompanyId());

        Quote quote = Quote.builder()
                .shippingRequest(shippingRequest)
                .fulfillmentCompany(fulfillmentCompany)
                .monthlyFee(dto.monthlyFee())
                .setupFee(dto.setupFee())
                .storageFee(dto.storageFee())
                .shippingFee(dto.shippingFee())
                .message(dto.message())
                .build();

        return quoteRepository.save(quote);
    }

    @Transactional(readOnly = true)
    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Quote getQuote(Long id) {
        return quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException(id));
    }

    public Quote updateQuote(Long id, QuoteUpdateDto dto) {
        Quote quote = getQuote(id);

        quote.update(
                dto.monthlyFee(),
                dto.setupFee(),
                dto.storageFee(),
                dto.shippingFee(),
                dto.message()
        );

        return quote;
    }

    public void deleteQuote(Long id) {
        Quote quote = getQuote(id);
        quoteRepository.delete(quote);
    }
}