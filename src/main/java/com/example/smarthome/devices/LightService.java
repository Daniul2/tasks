package com.example.smarthome.devices;

import org.springframework.stereotype.Service;

@Service
public class LightService {

    private final LightDevice lightDevice;

    public LightService(LightDevice lightDevice) {
        this.lightDevice = lightDevice;
    }

    public void turnOn() {
        lightDevice.turnOn();
    }

    public void turnOff() {
        lightDevice.turnOff();
    }

    public String getStatus() {
        return lightDevice.getStatus();
    }
}
