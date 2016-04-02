package com.clean.example.endtoend.broadbandAccessDevice.getGetails;

import com.clean.example.businessrequirements.broadbandAccessDevice.getGetails.GetBroadbandAccessDeviceDetailsAcceptanceTest;
import com.clean.example.dataproviders.database.broadbandaccessdevice.BroadbandAccessDeviceDatabaseDataProvider;
import com.clean.example.endtoend.EndToEndYatspecTest;
import com.clean.example.entrypoints.rest.broadbandaccessdevice.GetBroadbandAccessDeviceEndpoint;
import com.googlecode.yatspec.junit.LinkingNote;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Ignore;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore("TODO enable when production code is ready")
@LinkingNote(message = "Business Requirements: %s", links = {GetBroadbandAccessDeviceDetailsAcceptanceTest.class})
public class GetBroadbandAccessDeviceDetailsEndToEndTest extends EndToEndYatspecTest {

    private static final String HOSTNAME = "device1.hostname.com";
    private static final String SERIAL_NUMBER = "serialNumber1";

    @Autowired
    BroadbandAccessDeviceDatabaseDataProvider broadbandAccessDeviceDatabaseDataProvider;

    private int responseStatus;
    private String responseContent;

    @Test
    public void returnsTheDetailsOfADevice() throws Exception {
        givenADeviceInOurModel();

        whenTheApiToGetTheDeviceDetailsIsCalledForThatDevice();

        thenTheDetailsOfTheDeviceAreReturned();
    }

    private void givenADeviceInOurModel() {
        broadbandAccessDeviceDatabaseDataProvider.insert(HOSTNAME, SERIAL_NUMBER);
        log("Device in model", "Hostname: " + HOSTNAME + ", Serial Number: " + SERIAL_NUMBER);
    }

    private void whenTheApiToGetTheDeviceDetailsIsCalledForThatDevice() throws UnirestException {
        String apiPath = GetBroadbandAccessDeviceEndpoint.API_PATH.replace("{hostname}", HOSTNAME);
        String apiUrl = "http://localhost:8080" + apiPath;
        log("API Url", apiUrl);

        HttpResponse<String> httpResponse = Unirest.get(apiUrl).asString();

        responseStatus = httpResponse.getStatus();
        log("Response Status", responseStatus);

        responseContent = httpResponse.getBody();
        log("Response Content", responseContent); // TODO might need to prettify this?
    }

    private void thenTheDetailsOfTheDeviceAreReturned() {
        assertThat(responseStatus).isEqualTo(200);

        String expectedResponse = "TODO";
        JSONAssert.assertEquals(expectedResponse, responseContent, false);
    }

}
