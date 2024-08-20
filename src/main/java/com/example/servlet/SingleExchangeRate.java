package com.example.servlet;

import com.example.service.ExchangeRateService;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ExchangeRate/*")
public class SingleExchangeRate extends HttpServlet {
    private ExchangeRateService service;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = new ExchangeRateService();
        gson = new Gson();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Exchange rate is missing");
            return;
        }

        String currencyCode = pathInfo.substring(1,4);
        String currencyCode2 = pathInfo.substring(4,7);
        double exchangerate = service.getExchangeRateByCode(currencyCode,currencyCode2);
        String json = gson.toJson(exchangerate);
        resp.getWriter().write(json);
    }
}
