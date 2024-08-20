package com.example.servlet;

import com.example.dto.ExchangeRateDTO;
import com.example.service.ExchangeRateService;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ExchangeRates")
public class ExchangeRates extends HttpServlet {
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
        List<ExchangeRateDTO> list = service.getAllExchangeRates();
        String json = gson.toJson(list);
        resp.getWriter().write(json);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String base = req.getParameter("base");
        String target = req.getParameter("target");
        double rate = Double.parseDouble(req.getParameter("rate"));
        service.addExchangeRate(base,target,rate);
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String base = req.getParameter("base");
        String target = req.getParameter("target");
        double rate = Double.parseDouble(req.getParameter("rate"));
        service.changeExchangeRate(base,target,rate);
    }



}