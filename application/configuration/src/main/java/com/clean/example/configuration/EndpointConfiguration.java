package com.clean.example.configuration;

import com.clean.example.core.usecase.broadbandaccessdevice.getdetails.GetBroadbandAccessDeviceDetailsUseCase;
import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import com.clean.example.entrypoints.rest.broadbandaccessdevice.GetBroadbandAccessDeviceEndpoint;
import com.clean.example.entrypoints.rest.user.FindAllUsersEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfiguration {

    @Bean
    public FindAllUsersEndpoint findAllUsersEndpoint(FindAllUsersUseCase findAllUsersUseCase) {
        return new FindAllUsersEndpoint(findAllUsersUseCase);
    }

    @Bean
    public GetBroadbandAccessDeviceEndpoint getBroadbandAccessDeviceEndpoint(GetBroadbandAccessDeviceDetailsUseCase getBroadbandAccessDeviceDetailsUseCase) {
        return new GetBroadbandAccessDeviceEndpoint(getBroadbandAccessDeviceDetailsUseCase);
    }

}
