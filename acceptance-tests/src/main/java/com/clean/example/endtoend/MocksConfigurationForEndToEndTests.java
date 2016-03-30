package com.clean.example.endtoend;

import com.clean.example.dataproviders.network.broadbandaccessdevice.DeviceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class MocksConfigurationForEndToEndTests {

    @Bean
    public DeviceClient deviceClient() {
        return mock(DeviceClient.class);
    }

}
