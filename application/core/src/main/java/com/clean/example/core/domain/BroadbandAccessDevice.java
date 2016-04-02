package com.clean.example.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BroadbandAccessDevice {

    private Exchange exchange;

    private String hostname;
    private String serialNumber;

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

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
