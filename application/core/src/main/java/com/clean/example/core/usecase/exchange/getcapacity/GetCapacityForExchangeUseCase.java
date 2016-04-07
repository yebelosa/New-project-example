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
        boolean adsl = hasCapacityFor(devices, DeviceType.ADSL);
        boolean fibre = hasCapacityFor(devices, DeviceType.FIBRE);
        return new Capacity(adsl, fibre);
    }

    private boolean hasCapacityFor(List<BroadbandAccessDevice> devices, DeviceType deviceType) {
        int availablePorts = countAvailablePortsForType(devices, deviceType);
        return availablePorts >= MINIMUM_NUMBER_OF_PORTS;
    }

    private int countAvailablePortsForType(List<BroadbandAccessDevice> devices, DeviceType deviceType) {
        int availablePorts = 0;
        for (BroadbandAccessDevice device : devices) {
            if (device.getType() == deviceType) {
                availablePorts += device.getAvailablePorts();
            }
        }
        return availablePorts;
    }

}
