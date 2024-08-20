package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ExchangeRate {
    private int id;
    private String baseCurrencyCode;
    private String targetCurrencyCode;
    private double exchangeRate;

}
