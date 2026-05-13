package com.example.pipeline.steps;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


@Component
public class WatermarkStep implements ImageStep {

    @PostConstruct
    public void init() {
        System.out.println("WatermarkStep initialized – loading watermark assets...");
    }

    @Override
    public byte[] process(byte[] input) {
        System.out.println("Adding watermark...");
        return input; // mock
    }
}
