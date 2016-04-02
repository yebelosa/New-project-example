package com.clean.example.integration.database.broadbandaccessdevice;

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

    private void cleanUpDatabase() {
        jdbcTemplate.update("DELETE FROM CLEAN_ARCHITECTURE.BB_ACCESS_DEVICE");
        jdbcTemplate.update("DELETE FROM CLEAN_ARCHITECTURE.EXCHANGE");
    }

    private void givenAnExistingExchange() {
        String exchangeName = "Exchange for test";
        String exchangePostcode = "PostCode";
        exchangeDatabaseDataProvider.insert(new Exchange(EXCHANGE_CODE, exchangeName, exchangePostcode));
    }

    private void givenABroadbandAccessDevice(String hostname) {
        givenABroadbandAccessDevice(hostname, "aSerialNumber");
    }

    private void givenABroadbandAccessDevice(String hostname, String serialNumber) {
        broadbandAccessDeviceDatabaseDataProvider.insert(EXCHANGE_CODE, hostname, serialNumber);
    }
}
