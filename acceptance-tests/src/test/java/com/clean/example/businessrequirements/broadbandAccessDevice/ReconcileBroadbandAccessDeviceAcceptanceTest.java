package com.clean.example.businessrequirements.broadbandAccessDevice;

import com.clean.example.core.domain.BroadbandAccessDevice;
import com.clean.example.core.usecase.broadbandaccessdevice.GetDeviceDetailsFromModel;
import com.clean.example.core.usecase.broadbandaccessdevice.GetSerialNumberFromReality;
import com.clean.example.core.usecase.broadbandaccessdevice.ReconcileBroadbandAccessDevicesUseCase;
import com.clean.example.core.usecase.broadbandaccessdevice.UpdateSerialNumberInModel;
import com.clean.example.core.usecase.job.OnFailure;
import com.clean.example.core.usecase.job.OnSuccess;
import com.clean.example.dataproviders.network.deviceclient.DeviceConnectionTimeoutException;
import com.clean.example.endtoend.broadbandAccessDevice.ReconcileBroadbandAccessDeviceEndToEndTest;
import com.clean.example.yatspec.YatspecTest;
import com.googlecode.yatspec.junit.LinkingNote;
import com.googlecode.yatspec.junit.Notes;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@Ignore("TODO implement production code to make this pass")
@Notes("Reconciles information about Broadband Access Devices between what is stored in our model and the data that comes back from the actual device")
@LinkingNote(message = "End to End test: %s", links = {ReconcileBroadbandAccessDeviceEndToEndTest.class})
public class ReconcileBroadbandAccessDeviceAcceptanceTest extends YatspecTest {

    // This acceptance test documents the business requirements, and demonstrates that they are fullfilled
    // DO:
    //   - it tests all the details that are considered important from a business point of view
    // DON'T
    //   - doesn't test technical details (e.g. the web layer, or the fact that it's a scheduled job)
    //   - doesn't test low level details (e.g. weird exception scenarios, null values, etc.). they are left to the unit tests

    private static final String HOSTNAME = "device.hostname.com";
    private static final String SERIAL_NUMBER = "originalSerialNumber";
    private static final String NEW_SERIAL_NUMBER = "newSerialNumber";
    public static final String INVALID_SERIAL_NUMBER = "invalidSerialNumberBecauseItIsTooLong";

    GetDeviceDetailsFromModel getDeviceDetailsFromModel = mock(GetDeviceDetailsFromModel.class);
    GetSerialNumberFromReality getSerialNumberFromReality = mock(GetSerialNumberFromReality.class);
    UpdateSerialNumberInModel updateSerialNumberInModel = mock(UpdateSerialNumberInModel.class);
    OnSuccess onSuccess = mock(OnSuccess.class);
    OnFailure onFailure = mock(OnFailure.class);
    ReconcileBroadbandAccessDevicesUseCase reconcileBroadbandAccessDevicesUseCase = new ReconcileBroadbandAccessDevicesUseCase(getDeviceDetailsFromModel, getSerialNumberFromReality, updateSerialNumberInModel);

    @Test
    public void nothingToUpdateWhenModelAndRealityAreTheSame() throws Exception {
        givenADeviceInTheModel();
        givenADeviceInRealityWithTheSameSerialNumber();

        whenTheDeviceIsReconciled();

        thenTheDeviceInTheModelIsNotUpdated();
        thenNoSuccessOrFailureIsRecorded();
    }

    @Test
    public void updatesModelWhenRealityHasChanged() throws Exception {
        givenADeviceInTheModel();
        givenADeviceInRealityWithANewSerialNumber();

        whenTheDeviceIsReconciled();

        thenTheDeviceInTheModelIsUpdatedWithTheNewSerialNumber();
        thenASuccessIsRecorded();
    }

    @Test
    public void recordsAnErrorWhenRealityInformationIsInvalid() throws Exception {
        givenADeviceInTheModel();
        givenADeviceInRealityWithAnInvalidSerialNumber();

        whenTheDeviceIsReconciled();

        thenTheDeviceInTheModelIsNotUpdated();
        thenAnErrorIsRecored();
    }

    @Test
    public void recordsAnErrorWhenTheCommunicationWithTheDeviceHasProblems() throws Exception {
        givenADeviceInTheModel();
        givenADeviceInRealityThatIsNotResponding();

        whenTheDeviceIsReconciled();

        thenTheDeviceInTheModelIsNotUpdated();
        thenAnErrorIsRecored();
    }

    // GIVENs
    private void givenADeviceInTheModel() {
        BroadbandAccessDevice deviceInModel = new BroadbandAccessDevice(HOSTNAME, SERIAL_NUMBER);
        when(getDeviceDetailsFromModel.getDeviceDetails(HOSTNAME)).thenReturn(deviceInModel);
        log("Device in model before reconciliation", "Hostname: [" + deviceInModel.getHostname() + "], Serial Number: [" + deviceInModel.getSerialNumber() + "]");
    }

    private void givenADeviceInRealityWithTheSameSerialNumber() {
        when(getSerialNumberFromReality.getSerialNumber(HOSTNAME)).thenReturn(SERIAL_NUMBER);
        log("Device in reality", "Hostname: [" + HOSTNAME + "], Serial Number: [" + SERIAL_NUMBER + "]");
    }

    private void givenADeviceInRealityWithANewSerialNumber() {
        when(getSerialNumberFromReality.getSerialNumber(HOSTNAME)).thenReturn(NEW_SERIAL_NUMBER);
        log("Device in reality", "Hostname: [" + HOSTNAME + "], Serial Number: [" + NEW_SERIAL_NUMBER + "]");
    }

    private void givenADeviceInRealityWithAnInvalidSerialNumber() {
        when(getSerialNumberFromReality.getSerialNumber(HOSTNAME)).thenReturn(INVALID_SERIAL_NUMBER);
        log("Device in reality", "Hostname: [" + HOSTNAME + "], Serial Number: [" + INVALID_SERIAL_NUMBER + "]");
    }

    private void givenADeviceInRealityThatIsNotResponding() {
        when(getSerialNumberFromReality.getSerialNumber(HOSTNAME)).thenThrow(new DeviceConnectionTimeoutException());
        log("Device in reality", "Device is not responding");
    }

    // WHENs
    private void whenTheDeviceIsReconciled() {
        reconcileBroadbandAccessDevicesUseCase.reconcile(onSuccess, onFailure);
    }

    // THENs
    private void thenTheDeviceInTheModelIsNotUpdated() {
        verify(updateSerialNumberInModel, never()).updateSerialNumber(eq(HOSTNAME), anyString());
        log("Device in model after reconciliation", "Hostname: [" + HOSTNAME + "], Serial Number: [" + SERIAL_NUMBER + "]");
    }

    private void thenTheDeviceInTheModelIsUpdatedWithTheNewSerialNumber() {
        verify(updateSerialNumberInModel).updateSerialNumber(HOSTNAME, NEW_SERIAL_NUMBER);
        log("Device in model after reconciliation", "Hostname: [" + HOSTNAME + "], Serial Number: [" + NEW_SERIAL_NUMBER + "]");
    }

    private void thenNoSuccessOrFailureIsRecorded() {
        verify(onSuccess, never()).onSuccess();
        verify(onFailure, never()).onFailure();
    }

    private void thenASuccessIsRecorded() {
        verify(onSuccess).onSuccess();
        verify(onFailure, never()).onFailure();
    }

    private void thenAnErrorIsRecored() {
        verify(onSuccess, never()).onSuccess();
        verify(onFailure).onFailure();
    }

}
