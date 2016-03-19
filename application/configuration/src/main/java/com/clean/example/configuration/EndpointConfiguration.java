package com.clean.example.configuration;

import com.clean.example.core.HelloWorldUseCase;
import com.clean.example.entrypoints.HelloWorldEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public HelloWorldEndpoint helloWorldEndpoint(HelloWorldUseCase helloWorldUseCase) {
        return new HelloWorldEndpoint(helloWorldUseCase);
    }

}
