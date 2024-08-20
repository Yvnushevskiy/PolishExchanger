package com.example.servlet;

import com.example.service.CurrencyService;
import com.example.service.ExchangeRateService;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/exchange")
public class Exchange  extends HttpServlet {
    private CurrencyService service ;
    private Gson gson;
    private ExchangeRateService exchangeService ;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = new CurrencyService();
        exchangeService = new ExchangeRateService();
        gson = new Gson();
        super.init(config);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String baseCurrency = req.getParameter("from");
        String targetCurrency = req.getParameter("to");
        String amountParam = req.getParameter("amount");
        Double amount = Double.parseDouble(amountParam);

        double exchangeRate = exchangeService.getExchangeRateByCode(baseCurrency, targetCurrency);
        double result = Math.floor((exchangeRate * amount) * 100) / 100;
        String json = gson.toJson(result);
        res.setContentType("application/json");
        res.getWriter().write(json);

    }

}
