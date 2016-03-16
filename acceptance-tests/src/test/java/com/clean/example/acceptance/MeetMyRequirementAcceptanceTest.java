package com.clean.example.acceptance;

import org.junit.Test;

public class MeetMyRequirementAcceptanceTest extends YatspecTest {

    @Test
    public void yatspecWorks() throws Exception {
        thenSomethingIsLogged();
    }

    private void thenSomethingIsLogged() {
        log("log", "message");
    }

}
