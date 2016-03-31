package com.clean.example.core.usecase.broadbandaccessdevice;

import com.clean.example.core.domain.BroadbandAccessDevice;

public interface GetDeviceDetailsFromModel {

    BroadbandAccessDevice getDeviceDetails(String hostname);

}
