package com.example.pipeline.steps;

public interface ImageStep {
    byte[] process(byte[] input);
}
