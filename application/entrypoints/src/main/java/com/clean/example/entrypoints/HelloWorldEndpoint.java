package com.clean.example.entrypoints;

import com.clean.example.core.domain.Example;
import com.clean.example.core.usecase.HelloWorldUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HelloWorldEndpoint {

    private HelloWorldUseCase helloWorldUseCase;

    public HelloWorldEndpoint(HelloWorldUseCase helloWorldUseCase) {
        this.helloWorldUseCase = helloWorldUseCase;
    }

    @RequestMapping(value = "/hello", method = GET)
    public List<ExampleDto> hello(){
        List<Example> examples = helloWorldUseCase.getAllExamples();
        return toDtos(examples);
    }

    private List<ExampleDto> toDtos(List<Example> examples) {
        return examples.stream()
                .map(example -> new ExampleDto(example.getHostname()))
                .collect(Collectors.toList());
    }

    class ExampleDto {
        private String message;

        ExampleDto(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
