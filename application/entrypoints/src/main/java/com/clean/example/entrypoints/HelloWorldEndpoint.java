package com.clean.example.entrypoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/hello")
public class HelloWorldEndpoint {

    @GET
    @Produces(APPLICATION_JSON)
    public ExampleDto hello(){
        return new ExampleDto("hello world");
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
