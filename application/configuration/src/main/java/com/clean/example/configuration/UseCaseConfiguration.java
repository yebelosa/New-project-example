package com.clean.example.configuration;

import com.clean.example.core.usecase.GetAllExamples;
import com.clean.example.core.usecase.HelloWorldUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public HelloWorldUseCase helloWorldUseCase(GetAllExamples getAllExamples) {
        return new HelloWorldUseCase(getAllExamples);
    }

}
