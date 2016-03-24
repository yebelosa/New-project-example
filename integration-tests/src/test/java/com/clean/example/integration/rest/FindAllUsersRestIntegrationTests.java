package com.clean.example.integration.rest;

import com.clean.example.core.domain.User;
import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import com.clean.example.core.usecase.user.NoUsersFoundException;
import com.clean.example.entrypoints.user.FindAllUsersEndpoint;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class FindAllUsersRestIntegrationTests {

    // TODO make into a yastspec test

    FindAllUsersUseCase findAllUsersUseCase = mock(FindAllUsersUseCase.class);

    MockMvc mockMvc;
    private String responseContent;
    private int status;

    @Before
    public void setUp() throws Exception {
        initialiseEndpoint();
    }

    @Test
    public void allTheUsersAreReturnedAsJson() throws Exception {
        givenThereAreUsers();

        whenTheFindAllUserApiIsCalled();

        thenTheResponseIsSuccessful();
        thenAllUsersAreReturned();
    }

    @Test
    public void returns404WhenNoUserIsFound() throws Exception {
        givenThereAreNoUsers();

        whenTheFindAllUserApiIsCalled();

        thenTheResponseStatusCodeIs404();
    }

    private void initialiseEndpoint() {
        mockMvc = MockMvcBuilders.standaloneSetup(new FindAllUsersEndpoint(findAllUsersUseCase)).build();
    }

    private void givenThereAreUsers() {
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User("username1", "FirstName1", "LastName1"));
        allUsers.add(new User("username2", "FirstName2", "LastName2"));

        when(findAllUsersUseCase.findAllUsers()).thenReturn(allUsers);
    }

    private void givenThereAreNoUsers() {
        when(findAllUsersUseCase.findAllUsers()).thenThrow(new NoUsersFoundException());
    }

    private void whenTheFindAllUserApiIsCalled() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(FindAllUsersEndpoint.API_PATH))
                .andReturn();
        responseContent = mvcResult.getResponse().getContentAsString();
        status = mvcResult.getResponse().getStatus();
    }

    private void thenTheResponseIsSuccessful() {
        assertThat(status).isEqualTo(200);
    }

    private void thenAllUsersAreReturned() throws JSONException {
        String expectedResponse =
                "[\n" +
                "    {\n" +
                "        \"firstName\": \"FirstName1\",\n" +
                "        \"lastName\": \"LastName1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"FirstName2\",\n" +
                "        \"lastName\": \"LastName2\"\n" +
                "    }\n" +
                "]";
        JSONAssert.assertEquals(expectedResponse, responseContent, false);
    }

    private void thenTheResponseStatusCodeIs404() {
        assertThat(status).isEqualTo(404);
    }

}
