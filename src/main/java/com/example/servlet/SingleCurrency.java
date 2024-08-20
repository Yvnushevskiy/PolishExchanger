package com.example.servlet;

import com.example.service.CurrencyService;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.model.Currency;

import java.io.IOException;

@WebServlet("/currency/*")
public class SingleCurrency extends HttpServlet {
    private CurrencyService service ;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = new CurrencyService();
        gson = new Gson();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Currency name is missing");
                    return;
        }
        String currencyCode = pathInfo.substring(1);
        Currency currency = service.getCurrencyByCode(currencyCode);
        String json = gson.toJson(currency);
        resp.getWriter().write(json);
    }
}
