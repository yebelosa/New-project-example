package com.clean.example.entrypoints;

import com.clean.example.core.domain.User;
import com.clean.example.core.usecase.GetAllUsersUseCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllUsersEndpointTest {

    GetAllUsersUseCase getAllUsersUseCase = mock(GetAllUsersUseCase.class);

    GetAllUsersEndpoint getAllUsersEndpoint = new GetAllUsersEndpoint(getAllUsersUseCase);

    @Test
    public void retrievesAllUsers() throws Exception {
        givenThereAreUsers(
                user("username1", "FirstName1", "LastName1"),
                user("username2", "FirstName2", "LastName2")
        );

        List<UserDto> allUsers = getAllUsersEndpoint.getAllUsers();

        assertThat(allUsers).hasSize(2);
        thenUserHasBeenReturned(allUsers, "FirstName1", "LastName1");
        thenUserHasBeenReturned(allUsers, "FirstName2", "LastName2");
    }

    private void givenThereAreUsers(User... users) {
        List<User> allUsers = Arrays.asList(users);
        when(getAllUsersUseCase.getAllUsers()).thenReturn(allUsers);
    }

    private User user(String username, String firstName, String lastName) {
        return new User(username, firstName, lastName);
    }

    private void thenUserHasBeenReturned(List<UserDto> allUsers, String firstName, String lastName) {
        boolean containsUser = allUsers.stream().anyMatch(user ->
                user.getFirstName().equals(firstName)
                && user.getLastName().equals(lastName));
        assertThat(containsUser).isTrue();
    }

}