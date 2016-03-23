package com.clean.example.core.usecase;

import com.clean.example.core.domain.Example;

import java.util.List;

public class HelloWorldUseCase {

    private final GetAllExamples getAllExamples;

    public HelloWorldUseCase(GetAllExamples getAllExamples) {
        this.getAllExamples = getAllExamples;
    }

    public List<Example> getAllExamples() {
        return getAllExamples.getAllExamples();
    }

}
