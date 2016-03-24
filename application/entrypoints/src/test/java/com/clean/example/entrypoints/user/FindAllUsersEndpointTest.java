package com.clean.example.entrypoints.user;

import com.clean.example.core.domain.User;
import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import com.clean.example.core.usecase.user.NoUsersFoundException;
import com.clean.example.entrypoints.exception.NotFoundException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindAllUsersEndpointTest {

    FindAllUsersUseCase findAllUsersUseCase = mock(FindAllUsersUseCase.class);

    FindAllUsersEndpoint findAllUsersEndpoint = new FindAllUsersEndpoint(findAllUsersUseCase);

    @Test
    public void retrievesAllUsers() throws Exception {
        givenThereAreUsers(
                user("username1", "FirstName1", "LastName1"),
                user("username2", "FirstName2", "LastName2")
        );

        List<UserDto> allUsers = findAllUsersEndpoint.getAllUsers();

        assertThat(allUsers).hasSize(2);
        thenUserHasBeenReturned(allUsers, "FirstName1", "LastName1");
        thenUserHasBeenReturned(allUsers, "FirstName2", "LastName2");
    }

    @Test
    public void throwsNotFoundExceptionWhenNoUserIsFound() throws Exception {
        givenNoUserIsFound();

        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> findAllUsersEndpoint.getAllUsers());
    }

    private void givenThereAreUsers(User... users) {
        List<User> allUsers = Arrays.asList(users);
        when(findAllUsersUseCase.findAllUsers()).thenReturn(allUsers);
    }

    private void givenNoUserIsFound() {
        when(findAllUsersUseCase.findAllUsers()).thenThrow(new NoUsersFoundException());
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