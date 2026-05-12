package com.example.currency.provider;

public interface RateProvider {
    double getRate(String from, String to);
}
