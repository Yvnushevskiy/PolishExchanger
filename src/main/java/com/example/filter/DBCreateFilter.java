/*package com.example.filter;

import com.example.database.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter(urlPatterns = "/*")
public class DBCreateFilter  extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        DBCreate.resetTable();
        DBCreate.createCurrenciesTable();
        DBCreate.createExchangeRatesTable();
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
        super.doFilter(req, res, chain);
    }
}
*/