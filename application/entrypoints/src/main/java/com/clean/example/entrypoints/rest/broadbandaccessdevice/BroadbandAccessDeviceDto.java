package com.clean.example.entrypoints.rest.broadbandaccessdevice;

public class BroadbandAccessDeviceDto {

    private final String exchangeCode;
    private final String hostname;
    private final String serialNumber;

    public BroadbandAccessDeviceDto(String exchangeCode, String hostname, String serialNumber) {
        this.exchangeCode = exchangeCode;
        this.hostname = hostname;
        this.serialNumber = serialNumber;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public String getHostname() {
        return hostname;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
