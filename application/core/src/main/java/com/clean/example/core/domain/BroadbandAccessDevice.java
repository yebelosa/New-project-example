package com.clean.example.core.domain;

public class BroadbandAccessDevice {

    private final String hostname;
    private final String serialNumber;

    public BroadbandAccessDevice(String hostname, String serialNumber) {
        this.hostname = hostname;
        this.serialNumber = serialNumber;
    }

    public String getHostname() {
        return hostname;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

}
