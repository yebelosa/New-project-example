package com.clean.example.endtoend.broadbandAccessDevice;

import com.clean.example.businessrequirements.broadbandAccessDevice.ReconcileBroadbandAccessDeviceAcceptanceTest;
import com.clean.example.core.domain.BroadbandAccessDevice;
import com.clean.example.dataproviders.database.broadbandaccessdevice.BroadbandAccessDeviceDatabaseDataProvider;
import com.clean.example.dataproviders.network.deviceclient.DeviceClient;
import com.clean.example.dataproviders.network.deviceclient.DeviceConnectionTimeoutException;
import com.clean.example.endtoend.EndToEndYatspecTest;
import com.clean.example.entrypoints.job.ScheduledJob;
import com.googlecode.yatspec.junit.LinkingNote;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Ignore("TODO implement production code to make this pass")
@LinkingNote(message = "Business Requirements: %s", links = {ReconcileBroadbandAccessDeviceAcceptanceTest.class})
public class ReconcileBroadbandAccessDeviceEndToEndTest extends EndToEndYatspecTest {

    // this end-to-end test starts the application and fires up a real job, using a real database (but mocking third-parties)
    // it sits at the top of the testing pyramid for automated tests, as it's the most expensive type
    // DO:
    //   - it makes sure that all layers are integrated correctly and the critical user path works as expected
    //   - it mocks third-parties as they would make our build unreliable, and it's not really our job to test their code (we rely on a contract that has been tested separately)
    // DON't:
    //   - doesn't test all the details that could happen, they are tested in cheaper tests (e.g. unit, integration, acceptance)

    @Autowired
    ScheduledJob reconcileBroadbandAccessDeviceJob;

    @Autowired
    BroadbandAccessDeviceDatabaseDataProvider broadbandAccessDeviceDatabaseDataProvider;

    @Autowired
    DeviceClient mockDeviceClient;

    @Test
    public void jobAuditsSuccessesAndFailures() throws Exception {
        givenADeviceInTheModel(with(hostname("hostname1")), and(serialNumber("serial1")));
        givenADeviceInTheModel(with(hostname("hostname2")), and(serialNumber("serial2")));
        givenADeviceInTheModel(with(hostname("hostname3")), and(serialNumber("serial3")));

        givenADeviceInReality(with(hostname("hostname1")), and(serialNumber("serial1")));
        givenADeviceInReality(with(hostname("hostname2")), and(serialNumber("changed")));
        givenADeviceThatIsNotResponding(with(hostname("hostname3")));

        whenTheReconcileBroadbandAccessDeviceJobRuns();

        thenTheModelHasNotBeenUpdatedFor(hostname("hostname1"), with(serialNumber("serial1")));
        thenTheModelHasBeenUpdatedFor(hostname("hostname2"), to(serialNumber("changed")));
        thenTheModelHasNotBeenUpdatedFor(hostname("hostname3"), with(serialNumber("serial3")));
    }

    private void givenADeviceInTheModel(String hostname, String serialNumber) {
        broadbandAccessDeviceDatabaseDataProvider.insert(hostname, serialNumber);
    }

    private void givenADeviceInReality(String hostname, String serialNumber) {
        when(mockDeviceClient.getSerialNumber(hostname)).thenReturn(serialNumber);
    }

    private void givenADeviceThatIsNotResponding(String hostname) {
        when(mockDeviceClient.getSerialNumber(hostname)).thenThrow(new DeviceConnectionTimeoutException());
    }

    private String hostname(String hostname) {
        return hostname;
    }

    private String serialNumber(String serialNumber) {
        return serialNumber;
    }

    private void whenTheReconcileBroadbandAccessDeviceJobRuns() {
        reconcileBroadbandAccessDeviceJob.run();
    }

    private void thenTheModelHasBeenUpdatedFor(String hostname, String expectedSerialNumber) {
        BroadbandAccessDevice device = broadbandAccessDeviceDatabaseDataProvider.getByHostname(hostname);
        assertThat(device.getSerialNumber()).isEqualTo(expectedSerialNumber);
    }

    private void thenTheModelHasNotBeenUpdatedFor(String hostname, String expectedSerialNumber) {
        BroadbandAccessDevice device = broadbandAccessDeviceDatabaseDataProvider.getByHostname(hostname);
        assertThat(device.getSerialNumber()).isEqualTo(expectedSerialNumber);
    }

}
