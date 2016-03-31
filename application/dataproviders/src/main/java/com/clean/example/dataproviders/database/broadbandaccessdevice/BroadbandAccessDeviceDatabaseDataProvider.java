package com.clean.example.dataproviders.database.broadbandaccessdevice;

import com.clean.example.core.domain.BroadbandAccessDevice;
import com.clean.example.core.usecase.broadbandaccessdevice.GetDeviceDetailsFromModel;
import com.clean.example.core.usecase.broadbandaccessdevice.UpdateSerialNumberInModel;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class BroadbandAccessDeviceDatabaseDataProvider implements GetDeviceDetailsFromModel, UpdateSerialNumberInModel {

    private JdbcTemplate jdbcTemplate;

    public BroadbandAccessDeviceDatabaseDataProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BroadbandAccessDevice getDeviceDetails(String hostname) {
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap("SELECT hostname, serial_number FROM clean_architecture.bb_access_device WHERE hostname = ?", hostname);
            String resultHostname = (String) result.get("hostname");
            String resultSerialNumber = (String) result.get("serial_number");
            return new BroadbandAccessDevice(resultHostname, resultSerialNumber);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateSerialNumber(String hostname, String serialNumber) {
        jdbcTemplate.update("UPDATE clean_architecture.bb_access_device SET serial_number = ? WHERE hostname = ?", serialNumber, hostname);
    }

    public void insert(String hostname, String serialNumber) {
        jdbcTemplate.update("INSERT INTO clean_architecture.bb_access_device (id, hostname, serial_number, created_date) " +
                " VALUES (bb_access_device_id_seq.nextval, ?, ?, sysdate)", hostname, serialNumber);
    }

}
