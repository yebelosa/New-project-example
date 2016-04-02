package com.clean.example.core.usecase.broadbandaccessdevice.getdetails;

import com.clean.example.core.domain.BroadbandAccessDevice;

public interface GetDeviceDetails {

    BroadbandAccessDevice getDetails(String hostname);

}
