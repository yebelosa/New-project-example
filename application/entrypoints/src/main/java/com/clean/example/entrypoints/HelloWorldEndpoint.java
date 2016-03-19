package com.clean.example.entrypoints;

import com.clean.example.core.HelloWorldUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HelloWorldEndpoint {

    private HelloWorldUseCase helloWorldUseCase;

    public HelloWorldEndpoint(HelloWorldUseCase helloWorldUseCase) {
        this.helloWorldUseCase = helloWorldUseCase;
    }

    @RequestMapping(value = "/hello", method = GET)
    public ExampleDto hello(){
        String message = helloWorldUseCase.sayHello("clean architecture");
        return new ExampleDto(message);
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
