package com.clean.example.dataproviders.network.broadbandaccessdevice;

public class FakeDeviceClient implements DeviceClient {

    @Override
    public String getSerialNumber(String hostname) {
        return "mock me";
    }

}
