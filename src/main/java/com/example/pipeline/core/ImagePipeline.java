package com.example.pipeline.core;

import com.example.pipeline.steps.ImageStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImagePipeline {

    private final List<ImageStep> steps;

    public ImagePipeline(List<ImageStep> steps) {
        this.steps = steps;
    }

    public byte[] process(byte[] input) {
        byte[] current = input;
        for (ImageStep step : steps) {
            current = step.process(current);
        }
        return current;
    }
}
