package com.clean.example.dataproviders.database.broadbandaccessdevice;

import com.clean.example.core.domain.BroadbandAccessDevice;
import com.clean.example.core.domain.DeviceType;
import com.clean.example.core.domain.Exchange;
import com.clean.example.core.usecase.broadbandaccessdevice.getdetails.GetDeviceDetails;
import com.clean.example.core.usecase.broadbandaccessdevice.reconcile.GetAllDeviceHostnames;
import com.clean.example.core.usecase.broadbandaccessdevice.reconcile.GetSerialNumberFromModel;
import com.clean.example.core.usecase.broadbandaccessdevice.reconcile.UpdateSerialNumberInModel;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BroadbandAccessDeviceDatabaseDataProvider implements GetAllDeviceHostnames, GetSerialNumberFromModel, UpdateSerialNumberInModel, GetDeviceDetails {

    private JdbcTemplate jdbcTemplate;

    public BroadbandAccessDeviceDatabaseDataProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> getAllDeviceHostnames() {
        return jdbcTemplate.queryForList("SELECT hostname FROM clean_architecture.bb_access_device", String.class);
    }

    @Override
    public String getSerialNumber(String hostname) {
        try {
            return jdbcTemplate.queryForObject("SELECT serial_number FROM clean_architecture.bb_access_device WHERE hostname = ?", String.class, hostname);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateSerialNumber(String hostname, String serialNumber) {
        jdbcTemplate.update("UPDATE clean_architecture.bb_access_device SET serial_number = ? WHERE hostname = ?", serialNumber, hostname);
    }

    public void insert(String exchangeCode, String hostname, String serialNumber, DeviceType deviceType) {
        int availablePorts = 100;
        insert(exchangeCode, hostname, serialNumber, deviceType, availablePorts);
    }

    public void insert(String exchangeCode, String hostname, String serialNumber, DeviceType deviceType, int availablePorts) {
        jdbcTemplate.update("INSERT INTO clean_architecture.bb_access_device (id, exchange_id, hostname, serial_number, device_type_id, available_ports, created_date) " +
                " VALUES (bb_access_device_id_seq.nextval, " +
                " (SELECT id FROM clean_architecture.exchange WHERE code = ?), " +
                " ?, " +
                " ?, " +
                " (SELECT id FROM clean_architecture.device_type WHERE name = ?), " +
                " ?, " +
                "sysdate)", exchangeCode, hostname, serialNumber, deviceType.name(), availablePorts);
    }


    @Override
    public BroadbandAccessDevice getDetails(String hostname) {
        try {
            return doGetDetails(hostname);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    private BroadbandAccessDevice doGetDetails(String hostname) {
        Map<String, Object> result = jdbcTemplate.queryForMap(
                "SELECT e.code as ex_code, e.name as ex_name, e.postcode as ex_postcode, " +
                "       d.hostname, d.serial_number, dt.name as type, d.available_ports " +
                "FROM clean_architecture.bb_access_device d, clean_architecture.exchange e, clean_architecture.device_type dt " +
                "WHERE d.exchange_id = e.id " +
                "AND d.device_type_id = dt.id " +
                "AND d.hostname = ?"
                , hostname);

        String exchangeCode = (String) result.get("ex_code");
        String exchangeName = (String) result.get("ex_name");
        String exchangePostcode = (String) result.get("ex_postcode");
        Exchange exchange = new Exchange(exchangeCode, exchangeName, exchangePostcode);

        String resultHostname = (String) result.get("hostname");
        String resultSerialNumber = (String) result.get("serial_number");
        DeviceType deviceType = DeviceType.valueOf((String) result.get("type"));
        int availablePorts = ((BigDecimal) result.get("available_ports")).intValue();
        BroadbandAccessDevice device = new BroadbandAccessDevice(resultHostname, resultSerialNumber, deviceType);
        device.setAvailablePorts(availablePorts);
        device.setExchange(exchange);

        return device;
    }
}
