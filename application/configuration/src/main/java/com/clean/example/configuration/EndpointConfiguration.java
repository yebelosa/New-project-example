package com.clean.example.configuration;

import com.clean.example.core.usecase.GetAllUsersUseCase;
import com.clean.example.entrypoints.GetAllUsersEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public GetAllUsersEndpoint helloWorldEndpoint(GetAllUsersUseCase helloWorldUseCase) {
        return new GetAllUsersEndpoint(helloWorldUseCase);
    }

}
