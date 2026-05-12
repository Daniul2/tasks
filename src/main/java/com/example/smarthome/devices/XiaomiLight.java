package com.example.smarthome.devices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class XiaomiLight implements LightDevice{
    private boolean on = true;

    @Override
    public void turnOn() {
        on = true;
        System.out.println("Xiaomi Light: ON");
    }

    @Override
    public void turnOff() {
        on = false;
        System.out.println("Xiaomi Light: OFF");
    }

    @Override
    public String getStatus() {
        return on ? "Xiaomi light is ON" : "Xiaomi light is OFF";
    }
}
