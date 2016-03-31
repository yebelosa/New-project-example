package com.clean.example.integration.database.broadbandaccessdevice;

import com.clean.example.core.domain.BroadbandAccessDevice;
import com.clean.example.dataproviders.database.broadbandaccessdevice.BroadbandAccessDeviceDatabaseDataProvider;
import com.clean.example.integration.database.DatabaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class BroadbandAccessDeviceDatabaseIntegrationTest extends DatabaseIntegrationTest {

    @Autowired
    BroadbandAccessDeviceDatabaseDataProvider broadbandAccessDeviceDatabaseDataProvider;

    @Test
    public void getsDeviceDetails() throws Exception {
        givenABroadbandAccessDevice("hostname1", "serialNumber1");
        givenABroadbandAccessDevice("hostname2", "serialNumber2");

        BroadbandAccessDevice device1 = broadbandAccessDeviceDatabaseDataProvider.getDeviceDetails("hostname1");
        assertThat(device1.getSerialNumber()).isEqualTo("serialNumber1");

        BroadbandAccessDevice device2 = broadbandAccessDeviceDatabaseDataProvider.getDeviceDetails("hostname2");
        assertThat(device2.getSerialNumber()).isEqualTo("serialNumber2");
    }

    @Test
    public void updatesSerialNumberOfADevice() throws Exception {
        givenABroadbandAccessDevice("hostname1", "serialNumber1");

        broadbandAccessDeviceDatabaseDataProvider.updateSerialNumber("hostname1", "newSerialNumber");

        BroadbandAccessDevice device = broadbandAccessDeviceDatabaseDataProvider.getDeviceDetails("hostname1");
        assertThat(device.getSerialNumber()).isEqualTo("newSerialNumber");
    }

    private void givenABroadbandAccessDevice(String hostname, String serialNumber) {
        broadbandAccessDeviceDatabaseDataProvider.insert(hostname, serialNumber);
    }
}
