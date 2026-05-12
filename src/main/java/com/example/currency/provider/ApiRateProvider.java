package com.example.currency.provider;

import com.example.currency.config.CurrencyConfig;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Profile("prod")
public class ApiRateProvider implements RateProvider{
    private final CurrencyConfig config;
    private final RestTemplate rest = new RestTemplate();

    public ApiRateProvider(CurrencyConfig config) {
        this.config = config;
    }

    @Override
    public double getRate(String from, String to) {
        String url = config.getBaseUrl() + "?base=" + from + "&symbols=" + to;

        Map response = rest.getForObject(url, Map.class);
        Map<String, Double> rates = (Map<String, Double>) response.get("rates");

        return rates.get(to);
    }
}
