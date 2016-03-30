package com.clean.example.dataproviders.database.broadbandaccessdevice;

import org.springframework.jdbc.core.JdbcTemplate;

public class BroadbandAccessDeviceDatabaseDataProvider {

    private JdbcTemplate jdbcTemplate;

    public BroadbandAccessDeviceDatabaseDataProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(String hostname, String serialNumber) {
        jdbcTemplate.update("INSERT INTO CLEAN_ARCHITECTURE.BB_ACCESS_DEVICE (ID, HOSTNAME, SERIAL_NUMBER, CREATED_DATE) " +
                " VALUES (BB_ACCESS_DEVICE_ID_SEQ.NEXTVAL, ?, ?, SYSDATE)", hostname, serialNumber);
    }

}
