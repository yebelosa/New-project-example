package com.clean.example.integration.database.broadbandaccessdevice;

import com.clean.example.dataproviders.database.broadbandaccessdevice.BroadbandAccessDeviceDatabaseDataProvider;
import com.clean.example.integration.database.DatabaseIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BroadbandAccessDeviceDatabaseIntegrationTest extends DatabaseIntegrationTest {

    @Autowired
    BroadbandAccessDeviceDatabaseDataProvider broadbandAccessDeviceDatabaseDataProvider;

    @Test
    public void getsAllDeviceHostnames() throws Exception {
        givenABroadbandAccessDevice("hostname1");
        givenABroadbandAccessDevice("hostname2");

        List<String> allDeviceHostnames = broadbandAccessDeviceDatabaseDataProvider.getAllDeviceHostnames();

        assertThat(allDeviceHostnames).containsOnly("hostname1", "hostname2");
    }

    @Test
    public void getsTheSerialNumberOfADevice() throws Exception {
        givenABroadbandAccessDevice("hostname1", "serialNumber1");

        String serialNumber = broadbandAccessDeviceDatabaseDataProvider.getSerialNumber("hostname1");

        assertThat(serialNumber).isEqualTo("serialNumber1");
    }

    @Test
    public void updatesSerialNumberOfADevice() throws Exception {
        givenABroadbandAccessDevice("hostname1", "serialNumber1");

        broadbandAccessDeviceDatabaseDataProvider.updateSerialNumber("hostname1", "newSerialNumber");

        String serialNumber = broadbandAccessDeviceDatabaseDataProvider.getSerialNumber("hostname1");
        assertThat(serialNumber).isEqualTo("newSerialNumber");
    }

    private void givenABroadbandAccessDevice(String hostname) {
        givenABroadbandAccessDevice(hostname, "aSerialNumber");
    }

    private void givenABroadbandAccessDevice(String hostname, String serialNumber) {
        broadbandAccessDeviceDatabaseDataProvider.insert(hostname, serialNumber);
    }
}
