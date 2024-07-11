package com.halfacode.demo.repository;


import com.halfacode.demo.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findBySourceCurrencyAndTargetCurrency(String sourceCurrency,String targetCurrency);
}