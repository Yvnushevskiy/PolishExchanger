package com.example.servlet;

import com.example.model.Currency;
import com.example.service.CurrencyService;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/currencies")
public class Currencies extends HttpServlet {
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
        List<Currency> list = service.getAllCurrencies();
        String json = gson.toJson(list);
        resp.getWriter().write(json);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String Name = req.getParameter("Name");
      String Code = req.getParameter("Code");
      String Sign = req.getParameter("Sign");
      service.addCurrency(Name, Code, Sign);

    }
}
