package com.clean.example.integration.database.broadbandaccessdevice;

import com.clean.example.core.domain.BroadbandAccessDevice;
import com.clean.example.core.domain.DeviceType;
import com.clean.example.core.domain.Exchange;
import com.clean.example.dataproviders.database.broadbandaccessdevice.BroadbandAccessDeviceDatabaseDataProvider;
import com.clean.example.dataproviders.database.exchange.ExchangeDatabaseDataProvider;
import com.clean.example.integration.database.DatabaseIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BroadbandAccessDeviceDatabaseIntegrationTest extends DatabaseIntegrationTest {

    public static final String EXCHANGE_CODE = "exch1";
    public static final String EXCHANGE_NAME = "Exchange for test";
    public static final String EXCHANGE_POSTCODE = "PostCode";

    @Autowired
    BroadbandAccessDeviceDatabaseDataProvider broadbandAccessDeviceDatabaseDataProvider;

    @Autowired
    ExchangeDatabaseDataProvider exchangeDatabaseDataProvider;

    @Before
    public void setUp() throws Exception {
        cleanUpDatabase();
        givenAnExistingExchange();
    }

    @Test
    public void getsAllDeviceHostnames() throws Exception {
        givenABroadbandAccessDevice("hostname1");
        givenABroadbandAccessDevice("hostname2");

        List<String> allDeviceHostnames = broadbandAccessDeviceDatabaseDataProvider.getAllDeviceHostnames();

        assertThat(allDeviceHostnames).containsOnly("hostname1", "hostname2");
    }

    @Test
    public void getsTheSerialNumberOfADevice() throws Exception {
        givenABroadbandAccessDevice("hostname1", "serialNumber1", DeviceType.FIBRE);

        String serialNumber = broadbandAccessDeviceDatabaseDataProvider.getSerialNumber("hostname1");

        assertThat(serialNumber).isEqualTo("serialNumber1");
    }

    @Test
    public void updatesSerialNumberOfADevice() throws Exception {
        givenABroadbandAccessDevice("hostname1", "serialNumber1", DeviceType.FIBRE);

        broadbandAccessDeviceDatabaseDataProvider.updateSerialNumber("hostname1", "newSerialNumber");

        String serialNumber = broadbandAccessDeviceDatabaseDataProvider.getSerialNumber("hostname1");
        assertThat(serialNumber).isEqualTo("newSerialNumber");
    }

    @Test
    public void getsDeviceDetails() throws Exception {
        givenABroadbandAccessDevice("hostname1", "serialNumber1", DeviceType.FIBRE, 123);

        BroadbandAccessDevice device = broadbandAccessDeviceDatabaseDataProvider.getDetails("hostname1");

        assertThat(device.getHostname()).isEqualTo("hostname1");
        assertThat(device.getSerialNumber()).isEqualTo("serialNumber1");
        assertThat(device.getType()).isEqualTo(DeviceType.FIBRE);
        assertThat(device.getAvailablePorts()).isEqualTo(123);
        assertThat(device.getExchange().getCode()).isEqualTo(EXCHANGE_CODE);
        assertThat(device.getExchange().getName()).isEqualTo(EXCHANGE_NAME);
        assertThat(device.getExchange().getPostCode()).isEqualTo(EXCHANGE_POSTCODE);
    }

    private void cleanUpDatabase() {
        jdbcTemplate.update("DELETE FROM CLEAN_ARCHITECTURE.BB_ACCESS_DEVICE");
        jdbcTemplate.update("DELETE FROM CLEAN_ARCHITECTURE.EXCHANGE");
    }

    private void givenAnExistingExchange() {
        exchangeDatabaseDataProvider.insert(new Exchange(EXCHANGE_CODE, EXCHANGE_NAME, EXCHANGE_POSTCODE));
    }

    private void givenABroadbandAccessDevice(String hostname) {
        givenABroadbandAccessDevice(hostname, "aSerialNumber", DeviceType.FIBRE);
    }

    private void givenABroadbandAccessDevice(String hostname, String serialNumber, DeviceType deviceType) {
        broadbandAccessDeviceDatabaseDataProvider.insert(EXCHANGE_CODE, hostname, serialNumber, deviceType);
    }

    private void givenABroadbandAccessDevice(String hostname, String serialNumber, DeviceType deviceType, int availablePorts) {
        broadbandAccessDeviceDatabaseDataProvider.insert(EXCHANGE_CODE, hostname, serialNumber, deviceType, availablePorts);
    }
}
