package com.clean.example.endtoend;

import com.clean.example.businessrequirements.YatspecTest;
import org.junit.BeforeClass;
import org.springframework.boot.SpringApplication;

public abstract class EndToEndYatspecTest extends YatspecTest{

    @BeforeClass
    public static void setUp() throws Exception {
        SpringApplication.run(ApplicationConfigurationForEndToEndTests.class);
    }

}
