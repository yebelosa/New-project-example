package com.clean.example.dataproviders.database.broadbandaccessdevice;

import com.clean.example.core.domain.BroadbandAccessDevice;
import com.clean.example.core.domain.DeviceType;
import org.junit.Test;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class BroadbandAccessDeviceDatabaseDataProviderTest {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    BroadbandAccessDeviceDatabaseDataProvider broadbandAccessDeviceDatabaseDataProvider = new BroadbandAccessDeviceDatabaseDataProvider(jdbcTemplate);

    @Test
    public void returnsEmptyListWhenThereAreNoDevices() throws Exception {
        givenThereAreNoDevices();

        List<String> allDeviceHostnames = broadbandAccessDeviceDatabaseDataProvider.getAllDeviceHostnames();

        assertThat(allDeviceHostnames).isEmpty();
    }

    @Test
    public void returnsTheHostnameOfAllDevices() throws Exception {
        thereThereAreDevices("hostname1", "hostname2", "hostname3");

        List<String> allDeviceHostnames = broadbandAccessDeviceDatabaseDataProvider.getAllDeviceHostnames();

        assertThat(allDeviceHostnames).containsOnly("hostname1", "hostname2", "hostname3");
    }

    @Test
    public void returnsNullSerialNumberForADeviceThatDoesNotExist() throws Exception {
        givenDeviceDoesNotExist("hostname1");

        String serialNumber = broadbandAccessDeviceDatabaseDataProvider.getSerialNumber("hostname1");

        assertThat(serialNumber).isNull();
    }

    @Test
    public void returnsSerialNumberOfADevice() throws Exception {
        givenDeviceHasSerialNumber("hostname1", "serialNumber1");

        String serialNumber = broadbandAccessDeviceDatabaseDataProvider.getSerialNumber("hostname1");

        assertThat(serialNumber).isEqualTo("serialNumber1");
    }

    @Test
    public void updatesTheSerialNumberOfADevice() throws Exception {
        broadbandAccessDeviceDatabaseDataProvider.updateSerialNumber("hostname1", "newSerialNumber");

        verify(jdbcTemplate).update(anyString(), eq("newSerialNumber"), eq("hostname1"));
    }

    @Test
    public void returnsTheDetailsOfADevice() throws Exception {
        givenThereIsADevice("exchangeCode", "exchangeName", "exchangePostcode", "hostname", "serialNumber", DeviceType.ADSL, 123);

        BroadbandAccessDevice device = broadbandAccessDeviceDatabaseDataProvider.getDetails("hostname");

        assertThat(device.getHostname()).isEqualTo("hostname");
        assertThat(device.getSerialNumber()).isEqualTo("serialNumber");
        assertThat(device.getType()).isEqualTo(DeviceType.ADSL);
        assertThat(device.getAvailablePorts()).isEqualTo(123);
        assertThat(device.getExchange().getCode()).isEqualTo("exchangeCode");
        assertThat(device.getExchange().getName()).isEqualTo("exchangeName");
        assertThat(device.getExchange().getPostCode()).isEqualTo("exchangePostcode");
    }

    @Test
    public void returnsNullWhenTheDeviceIsNotFound() throws Exception {
        givenThereIsntADevice();

        BroadbandAccessDevice device = broadbandAccessDeviceDatabaseDataProvider.getDetails("hostname");

        assertThat(device).isNull();
    }

    private void givenThereAreNoDevices() {
        when(jdbcTemplate.queryForList(anyString(), eq(String.class))).thenReturn(new ArrayList<>());
    }

    private void givenDeviceDoesNotExist(String hostname) {
        when(jdbcTemplate.queryForObject(anyString(), eq(String.class), eq(hostname))).thenThrow(new IncorrectResultSizeDataAccessException(1));
    }

    private void givenDeviceHasSerialNumber(String hostname, String serialNumber) {
        when(jdbcTemplate.queryForObject(anyString(), eq(String.class), eq(hostname))).thenReturn(serialNumber);
    }

    private void givenThereIsADevice(String exchangeCode, String exchangeName, String exchangePostcode, String hostname, String serialNumber, DeviceType type, int availablePorts) {
        Map<String, Object> details = new HashMap<>();
        details.put("ex_code", exchangeCode);
        details.put("ex_name", exchangeName);
        details.put("ex_postcode", exchangePostcode);
        details.put("hostname", hostname);
        details.put("serial_number", serialNumber);
        details.put("type", type.name());
        details.put("available_ports", new BigDecimal(availablePorts));
        when(jdbcTemplate.queryForMap(anyString(), anyVararg())).thenReturn(details);
    }

    private void givenThereIsntADevice() {
        when(jdbcTemplate.queryForMap(anyString(), anyVararg())).thenThrow(new IncorrectResultSizeDataAccessException(1));
    }

    private void thereThereAreDevices(String... hostnames) {
        when(jdbcTemplate.queryForList(anyString(), eq(String.class))).thenReturn(Arrays.asList(hostnames));
    }

}