package com.clean.example.configuration;

import com.clean.example.core.usecase.user.GetAllUsers;
import com.clean.example.core.usecase.user.GetAllUsersUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetAllUsersUseCase helloWorldUseCase(GetAllUsers getAllExamples) {
        return new GetAllUsersUseCase(getAllExamples);
    }

}
