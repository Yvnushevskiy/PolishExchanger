package com.example.filter;

import com.example.database.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBCreate.resetTable();
        DBCreate.createCurrenciesTable();
        DBCreate.createExchangeRatesTable();
        CurrencyRepository repository = new CurrencyRepositoryImpl();
        ExchangeRateRepository exrepository = new ExchangeRateRepositoryImpl();

        // Добавление валюты
        repository.addCurrency("Euro", "EUR", "€");
        repository.addCurrency("Dollar", "USD", "$");
        repository.addCurrency("Rubble", "RUB", "₽");
        exrepository.addExchangeRate("EUR","EUR",1);
        exrepository.addExchangeRate("EUR","USD",1.2);
        exrepository.addExchangeRate("EUR","RUB",90);
        exrepository.addExchangeRate("USD","EUR",0.83);
        exrepository.addExchangeRate("USD","USD",1);
        exrepository.addExchangeRate("USD","RUB",85);
        exrepository.addExchangeRate("RUB","EUR",0.01111);
        exrepository.addExchangeRate("RUB","USD",0.01176);
        exrepository.addExchangeRate("RUB","RUB",1);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
    }
}
