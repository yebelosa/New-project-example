package com.clean.example.core.usecase.user;

import com.clean.example.core.domain.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindAllUsersUseCaseTest {

    FindAllUsers findAllUsers = mock(FindAllUsers.class);

    FindAllUsersUseCase findAllUsersUseCase = new FindAllUsersUseCase(findAllUsers);

    @Test
    public void retrievesAllUsersFromDataProvider() throws Exception {
        givenThereAreUsers(
                user("username1", "FirstName1", "LastName1"),
                user("username2", "FirstName2", "LastName2")
        );

        List<User> allUsers = findAllUsersUseCase.findAllUsers();

        assertThat(allUsers).hasSize(2);
        thenUserHasBeenReturned(allUsers, "username1", "FirstName1", "LastName1");
        thenUserHasBeenReturned(allUsers, "username2", "FirstName2", "LastName2");
    }

    @Test
    public void exceptionWhenNoUsersAreFound() throws Exception {
        givenNoUsersAreFound();

        assertThatExceptionOfType(NoUsersFoundException.class).isThrownBy(() -> findAllUsersUseCase.findAllUsers());
    }

    private void givenThereAreUsers(User... users) {
        List<User> allUsers = Arrays.asList(users);
        when(findAllUsers.findAllUsers()).thenReturn(allUsers);
    }

    private void givenNoUsersAreFound() {
        when(findAllUsers.findAllUsers()).thenReturn(new ArrayList<>());
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