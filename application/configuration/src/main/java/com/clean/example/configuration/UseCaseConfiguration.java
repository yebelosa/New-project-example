package com.clean.example.configuration;

import com.clean.example.core.usecase.user.FindAllUsers;
import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public FindAllUsersUseCase findAllUsersUseCase(FindAllUsers findAllUsers) {
        return new FindAllUsersUseCase(findAllUsers);
    }

}
