package com.clean.example.configuration;

import com.clean.example.core.HelloWorldUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public HelloWorldUseCase helloWorldUseCase() {
        return new HelloWorldUseCase();
    }

}
