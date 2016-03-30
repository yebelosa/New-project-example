package com.clean.example.endtoend.user;

import com.clean.example.endtoend.EndToEndYatspecTest;
import com.clean.example.entrypoints.rest.user.FindAllUsersEndpoint;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindAllUsersEndToEndTest extends EndToEndYatspecTest {

    int responseStatus;
    JsonNode responseContent;

    @Test
    public void findAllUsers() throws Exception {
        givenThereAreSomeUsers();

        whenTheFindAllUsersApiIsCalled();

        thenAllUsersAreReturnedSucessfully();
    }

    private void givenThereAreSomeUsers() {
        // relying on the ones already present for the sake of keeping this test simple
        // (otherwise we'd need to insert data, but since we're in a different session then the in-memory database wouldn't be enough anymore)
    }

    private void whenTheFindAllUsersApiIsCalled() throws UnirestException {
        HttpResponse<JsonNode> httpResponse = Unirest.get("http://localhost:8080" + FindAllUsersEndpoint.API_PATH).asJson();

        responseStatus = httpResponse.getStatus();
        responseContent = httpResponse.getBody();
    }

    private void thenAllUsersAreReturnedSucessfully() {
        assertThat(responseStatus).isEqualTo(200);

        JSONArray array = responseContent.getArray();
        assertThat(array.length()).isEqualTo(2);
        assertThat(array.getJSONObject(0).get("firstName")).isEqualTo("Mattia");
        assertThat(array.getJSONObject(1).get("firstName")).isEqualTo("Mary");
    }

}
