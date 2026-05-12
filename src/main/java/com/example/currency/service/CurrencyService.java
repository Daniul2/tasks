package com.example.currency.service;

import com.example.currency.provider.RateProvider;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final RateProvider provider;

    public CurrencyService(RateProvider provider) {
        this.provider = provider;
    }

    public double convert(String from, String to,double amount) {
        double rate = provider.getRate(from, to);
        return amount * rate;
    }
}
