package com.example.pipeline.steps;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class CompressStep implements ImageStep {

    @PostConstruct
    public void init() {
        System.out.println("CompressStep initialized – loading compression engine...");
    }

    @Override
    public byte[] process(byte[] input) {
        System.out.println("Compressing image...");
        return input; // mock
    }
}
