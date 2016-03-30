package com.clean.example.endtoend.broadbandAccessDevice;

import com.clean.example.businessrequirements.broadbandAccessDevice.ReconcileBroadbandAccessDeviceAcceptanceTest;
import com.clean.example.dataproviders.database.broadbandaccessdevice.BroadbandAccessDeviceDatabaseDataProvider;
import com.clean.example.dataproviders.network.broadbandaccessdevice.DeviceClient;
import com.clean.example.endtoend.EndToEndYatspecTest;
import com.clean.example.entrypoints.job.ScheduledJob;
import com.googlecode.yatspec.junit.LinkingNote;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@Ignore("TODO")
@LinkingNote(message = "Business Requirements: %s", links = {ReconcileBroadbandAccessDeviceAcceptanceTest.class})
public class ReconcileBroadbandAccessDeviceEndToEndTest extends EndToEndYatspecTest {

    // fire job, check database for model, check audit (in log file?); snmp is mocked

    @Autowired
    ScheduledJob reconcileBroadbandAccessDeviceJob;

    @Autowired
    BroadbandAccessDeviceDatabaseDataProvider broadbandAccessDeviceDatabaseDataProvider;

    @Autowired
    DeviceClient mockDeviceClient;

    @Test
    public void jobAuditsSuccessesAndFailures() throws Exception {
        givenABroadBandAccessDeviceInTheModel(hostname("hostname1"), serialNumber("serialA"));
        givenABroadBandAccessDeviceInTheModel(hostname("hostname2"), serialNumber("serialB"));
        givenABroadBandAccessDeviceInTheModel(hostname("hostname3"), serialNumber("serialC"));

        givenABroadBandAccessDeviceInReality(hostname("hostname1"), serialNumber("serialA"));
        givenABroadBandAccessDeviceInReality(hostname("hostname2"), serialNumber("changed"));
        givenABroadBandAccessDeviceThatIsNotResponding(hostname("hostname3"));

        whenTheReconcileBroadbandAccessDeviceJobRuns();

        thenTheModelHasBeenUpdatedFor(hostname("hostname1"));
        thenTheModelHasNotBeenUpdatedFor(hostname("hostname2"));
        thenTheModelHasNotBeenUpdatedFor(hostname("hostname3"));
    }

    private void givenABroadBandAccessDeviceInTheModel(String hostname, String serialNumber) {
        broadbandAccessDeviceDatabaseDataProvider.insert(hostname, serialNumber);
    }

    private void givenABroadBandAccessDeviceInReality(String hostname, String serialNumber) {
        when(mockDeviceClient.getSerialNumber(hostname)).thenReturn(serialNumber);
    }

    private void givenABroadBandAccessDeviceThatIsNotResponding(String hostname) {
        //don't prime it, so it won't respond
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

    private void thenTheModelHasBeenUpdatedFor(String hostname) {

        fail();
    }

    private void thenTheModelHasNotBeenUpdatedFor(String hostname) {
        fail();
    }

}
