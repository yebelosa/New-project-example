package com.clean.example.core.usecase;

import com.clean.example.core.domain.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllUsersUseCaseTest {

    GetAllUsers getAllUsers = mock(GetAllUsers.class);

    GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersUseCase(getAllUsers);

    @Test
    public void retrievesAllUsersFromDataProvider() throws Exception {
        givenThereAreUsers(
                user("username1", "FirstName1", "LastName1"),
                user("username2", "FirstName2", "LastName2")
        );

        List<User> allUsers = getAllUsersUseCase.getAllUsers();

        assertThat(allUsers).hasSize(2);
        thenUserHasBeenReturned(allUsers, "username1", "FirstName1", "LastName1");
        thenUserHasBeenReturned(allUsers, "username2", "FirstName2", "LastName2");
    }

    private void givenThereAreUsers(User... users) {
        List<User> allUsers = Arrays.asList(users);
        when(getAllUsers.getAllUsers()).thenReturn(allUsers);
    }

    private User user(String username, String firstName, String lastName) {
        return new User(username, firstName, lastName);
    }

    private void thenUserHasBeenReturned(List<User> allUsers, String username, String firstName, String lastName) {
        boolean containsUser = allUsers.stream().anyMatch(user ->
                user.getUsername().equals(username)
                && user.getFirstName().equals(firstName)
                && user.getLastName().equals(lastName));
        assertThat(containsUser).isTrue();
    }

}