package com.clean.example.core.usecase.exchange.getcapacity;

import com.clean.example.core.domain.BroadbandAccessDevice;
import com.clean.example.core.domain.Capacity;
import com.clean.example.core.domain.DeviceType;

import java.util.List;

public class GetCapacityForExchangeUseCase {

    private static final int MINIMUM_NUMBER_OF_PORTS = 5;

    private GetAvailablePortsOfAllDevicesInExchange getAvailablePortsOfAllDevicesInExchange;

    public GetCapacityForExchangeUseCase(GetAvailablePortsOfAllDevicesInExchange getAvailablePortsOfAllDevicesInExchange) {
        this.getAvailablePortsOfAllDevicesInExchange = getAvailablePortsOfAllDevicesInExchange;
    }

    public Capacity getCapacity(String exchangeCode) {
        List<BroadbandAccessDevice> devices = getAvailablePortsOfAllDevicesInExchange.getAvailablePortsOfAllDevicesInExchange(exchangeCode);

        int adslAvailablePorts = 0;
        int fibreAvailablePorts = 0;

        for (BroadbandAccessDevice device : devices) {
            if (device.getType() == DeviceType.ADSL) {
                adslAvailablePorts += device.getAvailablePorts();
            } else {
                fibreAvailablePorts += device.getAvailablePorts();
            }
        }

        boolean adsl = hasEnoughAvailablePorts(adslAvailablePorts);
        boolean fibre = hasEnoughAvailablePorts(fibreAvailablePorts);
        return new Capacity(adsl, fibre);
    }

    private boolean hasEnoughAvailablePorts(int availablePorts) {
        return availablePorts >= MINIMUM_NUMBER_OF_PORTS;
    }

}
