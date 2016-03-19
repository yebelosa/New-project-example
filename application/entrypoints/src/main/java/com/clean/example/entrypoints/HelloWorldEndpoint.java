package com.clean.example.entrypoints;

import com.clean.example.core.HelloWorldUseCase;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/hello")
public class HelloWorldEndpoint {

    private HelloWorldUseCase helloWorldUseCase;

    public HelloWorldEndpoint(HelloWorldUseCase helloWorldUseCase) {
        this.helloWorldUseCase = helloWorldUseCase;
    }

    @GET
    @Produces(APPLICATION_JSON)
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
