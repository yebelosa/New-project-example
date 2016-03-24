package com.clean.example.configuration;

import com.clean.example.core.usecase.user.FindAllUsers;
import com.clean.example.dataproviders.database.user.FindAllUsersFromDatabaseDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseDataProviderConfiguration {

    @Bean
    public FindAllUsers findAllUsers(JdbcTemplate jdbcTemplate) {
        return new FindAllUsersFromDatabaseDataProvider(jdbcTemplate);
    }

}
