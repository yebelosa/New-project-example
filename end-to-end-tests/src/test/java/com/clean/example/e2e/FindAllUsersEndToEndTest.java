package com.clean.example.e2e;

import com.clean.example.Application;
import com.clean.example.entrypoints.user.FindAllUsersEndpoint;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@IntegrationTest
@WebAppConfiguration
public class FindAllUsersEndToEndTest {

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
