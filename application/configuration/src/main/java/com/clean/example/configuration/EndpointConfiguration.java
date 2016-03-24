package com.clean.example.configuration;

import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import com.clean.example.entrypoints.user.FindAllUsersEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public FindAllUsersEndpoint findAllUsersEndpoint(FindAllUsersUseCase findAllUsersUseCase) {
        return new FindAllUsersEndpoint(findAllUsersUseCase);
    }

}
