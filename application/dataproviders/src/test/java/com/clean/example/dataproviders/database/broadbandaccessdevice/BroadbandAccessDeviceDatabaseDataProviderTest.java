package com.clean.example.dataproviders.database.broadbandaccessdevice;

import com.clean.example.core.domain.BroadbandAccessDevice;
import org.junit.Test;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class BroadbandAccessDeviceDatabaseDataProviderTest {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    BroadbandAccessDeviceDatabaseDataProvider broadbandAccessDeviceDatabaseDataProvider = new BroadbandAccessDeviceDatabaseDataProvider(jdbcTemplate);

    @Test
    public void returnsNullDeviceDetailsWhenDeviceDoesNotExist() throws Exception {
        givenDeviceDoesNotExist("hostname1");

        BroadbandAccessDevice deviceDetails = broadbandAccessDeviceDatabaseDataProvider.getDeviceDetails("hostname1");

        assertThat(deviceDetails).isNull();
    }

    @Test
    public void returnsDeviceDetails() throws Exception {
        givenADevice("hostname1", "serialNumber1");

        BroadbandAccessDevice deviceDetails = broadbandAccessDeviceDatabaseDataProvider.getDeviceDetails("hostname1");

        assertThat(deviceDetails.getHostname()).isEqualTo("hostname1");
        assertThat(deviceDetails.getSerialNumber()).isEqualTo("serialNumber1");
    }

    @Test
    public void updatesTheSerialNumberOfADevice() throws Exception {
        broadbandAccessDeviceDatabaseDataProvider.updateSerialNumber("hostname1", "newSerialNumber");

        verify(jdbcTemplate).update(anyString(), eq("newSerialNumber"), eq("hostname1"));
    }

    private void givenADevice(String hostname, String serialNumber) {
        Map<String, Object> results = new HashMap<>();
        results.put("hostname", hostname);
        results.put("serial_number", serialNumber);
        when(jdbcTemplate.queryForMap(anyString(), anyVararg())).thenReturn(results);
    }

    private void givenDeviceDoesNotExist(String hostname) {
        when(jdbcTemplate.queryForMap(anyString(), eq(hostname))).thenThrow(new IncorrectResultSizeDataAccessException(1));
    }
}