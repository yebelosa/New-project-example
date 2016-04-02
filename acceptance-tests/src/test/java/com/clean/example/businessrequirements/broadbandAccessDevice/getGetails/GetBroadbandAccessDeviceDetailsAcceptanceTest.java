package com.clean.example.businessrequirements.broadbandAccessDevice.getGetails;

import com.clean.example.core.domain.BroadbandAccessDevice;
import com.clean.example.core.usecase.broadbandaccessdevice.getdetails.DeviceNotFoundException;
import com.clean.example.core.usecase.broadbandaccessdevice.getdetails.GetBroadbandAccessDeviceDetailsUseCase;
import com.clean.example.core.usecase.broadbandaccessdevice.getdetails.GetDeviceDetails;
import com.clean.example.endtoend.broadbandAccessDevice.getGetails.GetBroadbandAccessDeviceDetailsEndToEndTest;
import com.clean.example.yatspec.YatspecTest;
import com.googlecode.yatspec.junit.LinkingNote;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore("TODO enable when production code is ready")
@LinkingNote(message = "End to End test: %s", links = {GetBroadbandAccessDeviceDetailsEndToEndTest.class})
public class GetBroadbandAccessDeviceDetailsAcceptanceTest extends YatspecTest {

    private static final String HOSTNAME = "device1.hostname.com";
    private static final String SERIAL_NUMBER = "serialNumber1";

    GetDeviceDetails getDeviceDetails = mock(GetDeviceDetails.class);

    GetBroadbandAccessDeviceDetailsUseCase getBroadbandAccessDeviceDetailsUseCase = new GetBroadbandAccessDeviceDetailsUseCase(getDeviceDetails);

    BroadbandAccessDevice deviceDetails;
    DeviceNotFoundException deviceNotFoundException;

    @Test
    public void returnsTheDetailsOfTheDevice() throws Exception {
        givenADeviceInOurModel();

        whenTheApiToGetTheDeviceDetailsIsCalledForThatDevice();

        thenTheDetailsOfTheDeviceAreReturned();
    }

    @Test
    public void errorWhenDeviceIsNotFound() throws Exception {
        givenTheDeviceDoesNotExist();

        whenTheApiToGetTheDeviceDetailsIsCalledForThatDevice();

        thenAnErrorIsReturned();
    }

    private void givenADeviceInOurModel() {
        BroadbandAccessDevice device = new BroadbandAccessDevice(HOSTNAME, SERIAL_NUMBER);
        when(getDeviceDetails.getDetails(HOSTNAME)).thenReturn(device);
        log("Device in model", device);
    }

    private void givenTheDeviceDoesNotExist() {
        when(getDeviceDetails.getDetails(HOSTNAME)).thenReturn(null);
    }

    private void whenTheApiToGetTheDeviceDetailsIsCalledForThatDevice() {
        try {
            deviceDetails = getBroadbandAccessDeviceDetailsUseCase.getDeviceDetails();
            log("Device details returned from API", deviceDetails);
        } catch (DeviceNotFoundException e) {
            this.deviceNotFoundException = e;
            log("Error received", deviceNotFoundException);
        }
    }

    private void thenTheDetailsOfTheDeviceAreReturned() {
        assertThat(deviceDetails.getHostname()).isEqualTo(HOSTNAME);
        assertThat(deviceDetails.getSerialNumber()).isEqualTo(SERIAL_NUMBER);
    }

    private void thenAnErrorIsReturned() {

    }

}
