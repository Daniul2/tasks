package com.example.smarthome.devices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class PhilipsLight implements LightDevice{
    private boolean on = false;
    @Override
    public void turnOn() {
        on = true;
        System.out.println("Philips Light: ON");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println("Philips Light: OFF");
    }

    @Override
    public String getStatus() {
        return on ? "Philips light is ON" : "Philips light is OFF";
    }
}
