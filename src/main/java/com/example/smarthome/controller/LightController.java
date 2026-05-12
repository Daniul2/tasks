package com.example.smarthome.controller;

import com.example.smarthome.devices.LightService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/light")
public class LightController {

    private final LightService lightService;

    public LightController(LightService lightService) {
        this.lightService = lightService;
    }

    @PostMapping("/on")
    public String turnOn() {
        lightService.turnOn();
        return "Light turned ON";
    }

    @PostMapping("/off")
    public String turnOff() {
        lightService.turnOff();
        return "Light turned OFF";
    }

    @GetMapping("/status")
    public String status() {
        return lightService.getStatus();
    }
}
