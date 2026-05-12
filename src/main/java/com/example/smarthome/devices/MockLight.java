package com.example.smarthome.devices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class MockLight implements LightDevice {

    private boolean on = false;

    @Override
    public void turnOn() {
        on = true;
        System.out.println("Mock Light: pretend ON");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println("Mock Light: pretend OFF");
    }

    @Override
    public String getStatus() {
        return on ? "Mock light is ON" : "Mock light is OFF";
    }
}
