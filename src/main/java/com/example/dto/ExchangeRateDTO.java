package com.example.dto;


import com.example.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class ExchangeRateDTO {
    int id;
    Currency baseCurrency;
    Currency targetCurrency;
    double ExchangeRate;
}
