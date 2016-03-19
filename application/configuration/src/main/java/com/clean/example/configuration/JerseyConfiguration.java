package com.clean.example.configuration;

import com.clean.example.entrypoints.HelloWorldEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/rest")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(HelloWorldEndpoint.class);
    }

}