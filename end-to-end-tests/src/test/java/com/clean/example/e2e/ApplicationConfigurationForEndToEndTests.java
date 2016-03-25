package com.clean.example.e2e;

import com.clean.example.configuration.JobConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.clean.example.configuration"},
        excludeFilters = @ComponentScan.Filter(value = JobConfiguration.class, type = FilterType.ASSIGNABLE_TYPE)
)
public class ApplicationConfigurationForEndToEndTests {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfigurationForEndToEndTests.class, args);
    }

}
