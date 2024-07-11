package com.halfacode.demo.service;

import com.halfacode.demo.dto.ExchangeRateDTO;
import com.halfacode.demo.entity.ExchangeRate;
import com.halfacode.demo.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public List<ExchangeRateDTO> findAll() {
        return exchangeRateRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ExchangeRateDTO findById(Long id) {
        return exchangeRateRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public ExchangeRateDTO save(ExchangeRateDTO exchangeRateDTO) {
        ExchangeRate exchangeRate = toEntity(exchangeRateDTO);
        exchangeRate = exchangeRateRepository.save(exchangeRate);
        return toDTO(exchangeRate);
    }

    public ExchangeRateDTO update(Long id, ExchangeRateDTO exchangeRateDTO) {
        if (exchangeRateRepository.existsById(id)) {
            ExchangeRate exchangeRate = toEntity(exchangeRateDTO);
            exchangeRate.setId(id);
            exchangeRate = exchangeRateRepository.save(exchangeRate);
            return toDTO(exchangeRate);
        }
        return null;
    }

    public void delete(Long id) {
        exchangeRateRepository.deleteById(id);
    }

    private ExchangeRateDTO toDTO(ExchangeRate exchangeRate) {
        ExchangeRateDTO dto = new ExchangeRateDTO();
        dto.setId(exchangeRate.getId());
        dto.setSourceCurrency(exchangeRate.getSourceCurrency());
        dto.setTargetCurrency(exchangeRate.getTargetCurrency());
        dto.setAmount(exchangeRate.getAmount());
        dto.setLastUpdate(exchangeRate.getLastUpdate());
        return dto;
    }

    private ExchangeRate toEntity(ExchangeRateDTO dto) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(dto.getId());
        exchangeRate.setSourceCurrency(dto.getSourceCurrency());
        exchangeRate.setTargetCurrency(dto.getTargetCurrency());
        exchangeRate.setAmount(dto.getAmount());
        exchangeRate.setLastUpdate(dto.getLastUpdate());
        return exchangeRate;
    }
}
