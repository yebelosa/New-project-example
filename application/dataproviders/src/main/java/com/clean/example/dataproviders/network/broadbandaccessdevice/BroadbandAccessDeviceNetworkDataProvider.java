package com.clean.example.dataproviders.network.broadbandaccessdevice;

import com.clean.example.core.usecase.broadbandaccessdevice.GetSerialNumberFromReality;

public class BroadbandAccessDeviceNetworkDataProvider implements GetSerialNumberFromReality{

    private DeviceClient deviceClient;

    public BroadbandAccessDeviceNetworkDataProvider(DeviceClient deviceClient) {
        this.deviceClient = deviceClient;
    }

    @Override
    public String getSerialNumber(String hostname) {
        return deviceClient.getSerialNumber(hostname);
    }

}
