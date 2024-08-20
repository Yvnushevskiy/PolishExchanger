package com.example.service;

import com.example.database.ExchangeRateRepository;
import com.example.database.ExchangeRateRepositoryImpl;
import com.example.dto.ExchangeRateDTO;
import com.example.model.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRateService {
    private ExchangeRateRepository Exrepository = new ExchangeRateRepositoryImpl();
    private CurrencyService CurrencyService = new CurrencyService();

    public List<ExchangeRateDTO> getAllExchangeRates(){
        List<ExchangeRate> list = Exrepository.getAllExchangeRates();
        List<ExchangeRateDTO> dtos = new ArrayList<ExchangeRateDTO>();
        for (ExchangeRate r : list) {
            ExchangeRateDTO dto = new ExchangeRateDTO();
            dto.setId(r.getId());
            dto.setBaseCurrency(CurrencyService.getCurrencyByCode(r.getBaseCurrencyCode()));
            dto.setTargetCurrency(CurrencyService.getCurrencyByCode(r.getTargetCurrencyCode()));
            dto.setExchangeRate(r.getExchangeRate());
            dtos.add(dto);
        }
        return dtos;
    }
    public double getExchangeRateByCode(String baseCurrencyCode, String targetCurrencyCode) {
        return Exrepository.getExchangeRateByCode(baseCurrencyCode, targetCurrencyCode);
    }
    public void addExchangeRate(String baseCurrencyCode, String targetCurrencyCode, double exchangeRate) {
        Exrepository.addExchangeRate(baseCurrencyCode, targetCurrencyCode, exchangeRate);
    }
    public void changeExchangeRate(String baseCurrencyCode, String targetCurrencyCode, double exchangeRate) {
        Exrepository.changeExchangeRate(baseCurrencyCode, targetCurrencyCode, exchangeRate);
    }
}
