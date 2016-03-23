package com.clean.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class DatasourceConfiguration {

    @Bean
    public DataSource dataSource() throws IOException {
        // we're using the in-memory h2 database for simplicity for this example.
        // For more info on h2 see http://www.h2database.com/html/features.html
        String h2InitialisationScript = new ClassPathResource("h2-schema.sql").getFile().getAbsolutePath();

        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriverClass(org.h2.Driver.class);
        simpleDriverDataSource.setUrl("jdbc:h2:mem:example;MODE=Oracle;INIT=runscript from '" + h2InitialisationScript + "'");
        simpleDriverDataSource.setUsername("CLEAN_ARCHITECTURE");
        simpleDriverDataSource.setPassword("");
        return simpleDriverDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

}
