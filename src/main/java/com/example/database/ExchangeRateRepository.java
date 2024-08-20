package com.example.database;

import com.example.dto.ExchangeRateDTO;
import com.example.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateRepository {
    void addExchangeRate(String baseCurrency, String targetCurrency, double exchangeRate);
    ExchangeRateDTO getExchangeRate(String baseCurrencyName, String targetCurrencyName);

    double getExchangeRateByCode(String baseCurrencyCode, String targetCurrencyCode);

    List<ExchangeRate> getAllExchangeRates();

    void changeExchangeRate(String baseCurrencyName, String targetCurrencyName, double exchangeRate);
}
