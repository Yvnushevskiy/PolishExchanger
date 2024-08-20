package com.example.service;

import com.example.database.CurrencyRepository;
import com.example.database.CurrencyRepositoryImpl;
import com.example.model.Currency;

import java.util.List;

public class CurrencyService   {
    private CurrencyRepository repository = new CurrencyRepositoryImpl();;
    public List<Currency> getAllCurrencies(){
        return repository.getAllCurrencies();
    }
    public Currency getCurrencyByCode(String code){
        return repository.getCurrencyByCode(code);
    }
    public Currency addCurrency(String name, String code, String sign){
        return repository.addCurrency(name,code,sign);
    }
    public Currency getCurrencyById(int id){
        return repository.getCurrencyById(id);
    }

}
