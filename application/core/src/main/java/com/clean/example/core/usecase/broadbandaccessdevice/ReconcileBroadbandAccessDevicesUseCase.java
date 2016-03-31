package com.clean.example.core.usecase.broadbandaccessdevice;

import com.clean.example.core.usecase.job.OnFailure;
import com.clean.example.core.usecase.job.OnSuccess;

public class ReconcileBroadbandAccessDevicesUseCase {

    public ReconcileBroadbandAccessDevicesUseCase(GetDeviceDetailsFromModel getDeviceDetailsFromModel, GetSerialNumberFromReality getSerialNumberFromReality, UpdateSerialNumberInModel updateSerialNumberInModel) {

    }

    public void reconcile(OnSuccess onSuccess, OnFailure onFailure) {

        //getAllModelDevices
        //loop
            //getReality
            //update if needed
            // call onsuccess or onfailure

    }

}
