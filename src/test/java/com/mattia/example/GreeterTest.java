package com.mattia.example;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GreeterTest {

    @Test
    public void greetsByName() throws Exception {
        Greeter greeter = new Greeter();

        String message = greeter.greet("Mattia");

        assertThat(message).isEqualTo("Hello Mattia!") ;
    }
    

}