package com.halfacode.demo.controller;

import com.halfacode.demo.dto.ExchangeRateDTO;
import com.halfacode.demo.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchange-rates")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping
    public List<ExchangeRateDTO> getAllExchangeRates() {
        return exchangeRateService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRateDTO> getExchangeRateById(@PathVariable Long id) {
        ExchangeRateDTO exchangeRateDTO = exchangeRateService.findById(id);
        if (exchangeRateDTO != null) {
            return ResponseEntity.ok(exchangeRateDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ExchangeRateDTO createExchangeRate(@RequestBody ExchangeRateDTO exchangeRateDTO) {
        return exchangeRateService.save(exchangeRateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExchangeRateDTO> updateExchangeRate(@PathVariable Long id, @RequestBody ExchangeRateDTO exchangeRateDTO) {
        ExchangeRateDTO updatedExchangeRateDTO = exchangeRateService.update(id, exchangeRateDTO);
        if (updatedExchangeRateDTO != null) {
            return ResponseEntity.ok(updatedExchangeRateDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExchangeRate(@PathVariable Long id) {
        exchangeRateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
