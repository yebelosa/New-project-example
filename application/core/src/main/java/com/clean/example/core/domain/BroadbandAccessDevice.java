package com.clean.example.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
