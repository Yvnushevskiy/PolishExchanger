package com.example.database;

import com.example.model.Currency;
import com.example.model.ExchangeRate;

import java.util.List;

public class Doing {
    public static void main(String[] args) {
        DBCreate.createCurrenciesTable();
        DBCreate.createExchangeRatesTable();
        DBCreate.resetTable();
        CurrencyRepositoryImpl.clearAllCurrencies();
        CurrencyRepository repository = new CurrencyRepositoryImpl();
        ExchangeRateRepository Exrepository = new ExchangeRateRepositoryImpl();

        // Добавление валюты
        repository.addCurrency("Euro", "EUR", "€");
        repository.addCurrency("Dollar", "USD", "$");
        repository.addCurrency("Rubble", "RUB", "₽");
        Exrepository.addExchangeRate("EUR","EUR",1);
        Exrepository.addExchangeRate("EUR","USD",1.2);
        Exrepository.addExchangeRate("EUR","RUB",90);
        Exrepository.addExchangeRate("USD","EUR",0.83);
        Exrepository.addExchangeRate("USD","USD",1);
        Exrepository.addExchangeRate("USD","RUB",85);
        Exrepository.addExchangeRate("RUB","EUR",0.01111);
        Exrepository.addExchangeRate("RUB","USD",0.01176);
        Exrepository.addExchangeRate("RUB","RUB",1);
        // Получение валюты по ID
        Currency currency = repository.getCurrencyById(1);
        System.out.println("Currency: " + currency.getName());

        // Получение всех валют
        List<Currency> allCurrencies = repository.getAllCurrencies();
        for (Currency c : allCurrencies) {
            System.out.println(c.getName());
        }
        List<ExchangeRate> allExchangeRates = Exrepository.getAllExchangeRates();
        for (ExchangeRate r : allExchangeRates) {
            System.out.println(r.getExchangeRate());
        }
    }
}
