package com.clean.example.dataproviders.database.exchange;

import com.clean.example.core.domain.Exchange;
import org.springframework.jdbc.core.JdbcTemplate;

public class ExchangeDatabaseDataProvider {

    private JdbcTemplate jdbcTemplate;

    public ExchangeDatabaseDataProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Exchange exchange) {
        jdbcTemplate.update("INSERT INTO CLEAN_ARCHITECTURE.EXCHANGE (ID, CODE, NAME, POSTCODE, CREATED_DATE) VALUES (CLEAN_ARCHITECTURE.EXCHANGE_ID_SEQ.nextval, ?, ?, ?, SYSDATE)", exchange.getCode(), exchange.getName(), exchange.getPostCode());
    }

}
