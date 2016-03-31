package com.clean.example.dataproviders.database.broadbandaccessdevice;

import com.clean.example.core.domain.BroadbandAccessDevice;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class BroadbandAccessDeviceDatabaseDataProvider {

    private JdbcTemplate jdbcTemplate;

    public BroadbandAccessDeviceDatabaseDataProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(String hostname, String serialNumber) {
        jdbcTemplate.update("INSERT INTO clean_architecture.bb_access_device (id, hostname, serial_number, created_date) " +
                " VALUES (bb_access_device_id_seq.nextval, ?, ?, sysdate)", hostname, serialNumber);
    }

    public BroadbandAccessDevice getByHostname(String hostname) {
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT hostname, serial_number FROM clean_architecture.bb_access_device WHERE hostname = ?", hostname);
        String resultHostname = (String) result.get("hostname");
        String resultSerialNumber = (String) result.get("serial_number");
        return new BroadbandAccessDevice(resultHostname, resultSerialNumber);
    }
}
