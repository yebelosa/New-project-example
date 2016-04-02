package com.clean.example.entrypoints.rest.broadbandaccessdevice;

import com.clean.example.core.usecase.broadbandaccessdevice.getdetails.GetBroadbandAccessDeviceDetailsUseCase;

public class GetBroadbandAccessDeviceEndpoint {

    public static final String API_PATH = "/broadbandaccessdevice/{hostname}";

    public GetBroadbandAccessDeviceEndpoint(GetBroadbandAccessDeviceDetailsUseCase getBroadbandAccessDeviceDetailsUseCase) {

    }
}
