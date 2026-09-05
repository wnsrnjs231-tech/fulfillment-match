package com.fulfillment.match.controller;

import com.fulfillment.match.domain.Quote;
import com.fulfillment.match.dto.QuoteCreateDto;
import com.fulfillment.match.dto.QuoteUpdateDto;
import com.fulfillment.match.service.QuoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public List<Quote> getQuotes() {
        return quoteService.getQuotes();
    }

    @GetMapping("/{id}")
    public Quote getQuote(@PathVariable Long id) {
        return quoteService.getQuote(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quote createQuote(@Valid @RequestBody QuoteCreateDto dto) {
        return quoteService.createQuote(dto);
    }

    @PutMapping("/{id}")
    public Quote updateQuote(
            @PathVariable Long id,
            @Valid @RequestBody QuoteUpdateDto dto
    ) {
        return quoteService.updateQuote(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
    }
}