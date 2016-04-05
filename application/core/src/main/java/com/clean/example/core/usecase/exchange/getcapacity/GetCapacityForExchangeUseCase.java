package com.clean.example.core.usecase.exchange.getcapacity;

import com.clean.example.core.domain.Capacity;

public class GetCapacityForExchangeUseCase {

    public GetCapacityForExchangeUseCase(GetAvailablePortsOfAllDevicesInExchange getAvailablePortsOfAllDevicesInExchange) {

    }

    public Capacity getCapacity(String exchangeCode) {
        // Exchange exchange = getExchangeFromModel(exchangeCode);
        // loop(exchange.getDevices()) {
        // int ports = device.getAvailablePorts();
        // add to total for bb or fibre
        // }
        // decide getcapacity
        // needs at least 5 available ports
        return new Capacity(false, false);
    }

}
