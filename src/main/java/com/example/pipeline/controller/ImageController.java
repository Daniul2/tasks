package com.example.pipeline.controller;

import com.example.pipeline.core.ImagePipeline;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    private final ImagePipeline pipeline;

    public ImageController(ImagePipeline pipeline) {
        this.pipeline = pipeline;
    }
    @PostMapping(value = "/process", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] process(@RequestBody byte[] image) {
        return pipeline.process(image);
    }
}
