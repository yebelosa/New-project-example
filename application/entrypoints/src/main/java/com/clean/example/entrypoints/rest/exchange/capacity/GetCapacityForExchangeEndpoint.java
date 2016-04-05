package com.clean.example.entrypoints.rest.exchange.capacity;

import com.clean.example.core.usecase.exchange.getcapacity.GetCapacityForExchangeUseCase;

public class GetCapacityForExchangeEndpoint {
    public static final String API_PATH = "/exchange/{exchangeCode}/capacity";

    public GetCapacityForExchangeEndpoint(GetCapacityForExchangeUseCase getCapacityForExchangeUseCase) {

    }
}
