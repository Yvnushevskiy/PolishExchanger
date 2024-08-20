package com.example.database;


import com.example.model.Currency;

import java.util.List;

public interface CurrencyRepository {
    Currency addCurrency(String name, String code, String sign);
    Currency getCurrencyById(int id);
    List<Currency> getAllCurrencies();
    Currency getCurrencyByCode(String code);
}
