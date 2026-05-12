package com.example.currency.provider;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class MockRateProvider implements RateProvider{
    @Override
    public double getRate(String from, String to) {
        return 4.20;
    }
}
