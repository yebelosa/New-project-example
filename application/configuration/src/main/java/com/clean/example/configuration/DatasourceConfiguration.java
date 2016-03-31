package com.clean.example.configuration;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DatasourceConfiguration {

    @Bean
    public DataSource dataSource() throws IOException {
        // we're using the in-memory h2 database for simplicity for this example.
        // For more info on h2 see http://www.h2database.com/html/features.html
        String h2InitialisationScript = new ClassPathResource("h2-schema.sql").getFile().getAbsolutePath();

        String jdbcUrl = "jdbc:h2:mem:example;MODE=Oracle;INIT=runscript from '" + h2InitialisationScript + "'";
        String username = "CLEAN_ARCHITECTURE";
        String password = "";
        return JdbcConnectionPool.create(jdbcUrl, username, password);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
