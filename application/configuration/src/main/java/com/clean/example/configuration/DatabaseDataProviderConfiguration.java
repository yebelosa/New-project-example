package com.clean.example.configuration;

import com.clean.example.core.usecase.user.GetAllUsers;
import com.clean.example.dataproviders.database.user.GetAllExamplesFromDatabaseDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseDataProviderConfiguration {

    @Bean
    public GetAllUsers getAllExamples(JdbcTemplate jdbcTemplate) {
        return new GetAllExamplesFromDatabaseDataProvider(jdbcTemplate);
    }

}
