package com.clean.example.configuration;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration {

    public static final String REST_MAPPING_PATH = "/rest/*";

    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new ServletContainer(), REST_MAPPING_PATH);
        servlet.addInitParameter("jersey.config.server.provider.packages", "com.clean.example.entrypoints");
        return servlet;
    }


}