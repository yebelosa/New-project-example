package com.clean.example.configuration;

import com.clean.example.core.usecase.broadbandaccessdevice.GetDeviceDetailsFromModel;
import com.clean.example.core.usecase.broadbandaccessdevice.GetSerialNumberFromReality;
import com.clean.example.core.usecase.broadbandaccessdevice.ReconcileBroadbandAccessDevicesUseCase;
import com.clean.example.core.usecase.broadbandaccessdevice.UpdateSerialNumberInModel;
import com.clean.example.core.usecase.user.FindAllUsers;
import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public FindAllUsersUseCase findAllUsersUseCase(FindAllUsers findAllUsers) {
        return new FindAllUsersUseCase(findAllUsers);
    }

    @Bean
    public ReconcileBroadbandAccessDevicesUseCase reconcileBroadbandAccessDevicesUseCas(GetDeviceDetailsFromModel getDeviceDetailsFromMode, GetSerialNumberFromReality getSerialNumberFromReality, UpdateSerialNumberInModel updateSerialNumberInModel) {
        return new ReconcileBroadbandAccessDevicesUseCase(getDeviceDetailsFromMode, getSerialNumberFromReality, updateSerialNumberInModel);
    }

}
