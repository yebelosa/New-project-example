package com.clean.example.core.usecase.broadbandaccessdevice;

import com.clean.example.core.usecase.job.OnFailure;
import com.clean.example.core.usecase.job.OnSuccess;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ReconcileBroadbandAccessDevicesUseCaseTest {

    GetAllDeviceHostnames getAllDevicesHostname = mock(GetAllDeviceHostnames.class);
    GetSerialNumberFromModel getSerialNumberFromModel = mock(GetSerialNumberFromModel.class);
    GetSerialNumberFromReality getSerialNumberFromReality = mock(GetSerialNumberFromReality.class);
    UpdateSerialNumberInModel updateSerialNumberInModel = mock(UpdateSerialNumberInModel.class);

    OnSuccess onSuccess = mock(OnSuccess.class);
    OnFailure onFailure = mock(OnFailure.class);

    ReconcileBroadbandAccessDevicesUseCase reconcileBroadbandAccessDevicesUseCase = new ReconcileBroadbandAccessDevicesUseCase(getAllDevicesHostname, getSerialNumberFromModel, getSerialNumberFromReality, updateSerialNumberInModel);

    @Test
    public void nothingToUpdateWhenModeAndRealityAreTheSame() throws Exception {
        givenThereIsADeviceWithHostname("hostname1");
        givenDeviceHasSerialNumberInModel("hostname1", "serialNumber1");
        givenDeviceHasSerialNumberInReality("hostname1", "serialNumber1");

        reconcileBroadbandAccessDevicesUseCase.reconcile(onSuccess, onFailure);

        thenNothingHasBeenUpdated();
    }

    private void givenThereIsADeviceWithHostname(String... hostnames) {
        when(getAllDevicesHostname.getAllDeviceHostnames()).thenReturn(Arrays.asList(hostnames));
    }

    private void givenDeviceHasSerialNumberInModel(String hostname, String serialNumber) {
        when(getSerialNumberFromModel.getSerialNumber(hostname)).thenReturn(serialNumber);
    }

    private void givenDeviceHasSerialNumberInReality(String hostname, String serialNumber) {
        when(getSerialNumberFromReality.getSerialNumber(hostname)).thenReturn(serialNumber);
    }

    private void thenNothingHasBeenUpdated() {
        verify(updateSerialNumberInModel, never()).updateSerialNumber(anyString(), anyString());
    }
}