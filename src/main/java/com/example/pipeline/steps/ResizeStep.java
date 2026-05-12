package com.example.pipeline.steps;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ResizeStep implements ImageStep {

    @PostConstruct
    public void init() {
        System.out.println("ResizeStep initialized – loading resize model...");
    }

    @Override
    public byte[] process(byte[] input) {
        System.out.println("Resizing image...");
        return input; // mock
    }
}
