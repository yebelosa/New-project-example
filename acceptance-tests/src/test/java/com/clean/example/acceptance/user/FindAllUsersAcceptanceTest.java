package com.clean.example.acceptance.user;

import com.clean.example.acceptance.YatspecTest;
import com.clean.example.core.domain.User;
import com.clean.example.core.usecase.user.FindAllUsers;
import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FindAllUsersAcceptanceTest extends YatspecTest {

    FindAllUsers findAllUsers = mock(FindAllUsers.class);

    FindAllUsersUseCase findAllUsersUseCase = new FindAllUsersUseCase(findAllUsers);

    List<User> allUsersFound;

    @Test
    public void returnsAllUsers() throws Exception {
        givenThereAreSomeUsers();

        whenWeFindAllUsers();

        thenAllUsersAreFound();
    }

    private void givenThereAreSomeUsers() {
        List<User> allUsersInTheSystem = new ArrayList<>();
        allUsersInTheSystem.add(user("username1", "FirstName1", "LastName1"));
        allUsersInTheSystem.add(user("username2", "FirstName2", "LastName2"));
        Mockito.when(findAllUsers.findAllUsers()).thenReturn(allUsersInTheSystem);
        log("Users in the system", allUsersInTheSystem);
    }

    private void whenWeFindAllUsers() {
        allUsersFound = findAllUsersUseCase.findAllUsers();
        log("All users found", allUsersFound);
    }

    private void thenAllUsersAreFound() {
        assertThat(allUsersFound).hasSize(2);
        userHasBeenFound(user("username1", "FirstName1", "LastName1"));
        userHasBeenFound(user("username2", "FirstName2", "LastName2"));
    }

    private void userHasBeenFound(User expectedUser) {
        boolean containsUser = allUsersFound.stream().anyMatch(user ->
                user.getUsername().equals(expectedUser.getUsername())
                && user.getFirstName().equals(expectedUser.getFirstName())
                && user.getLastName().equals(expectedUser.getLastName()));
        assertThat(containsUser).isTrue();
    }

    private User user(String username, String firstName, String lastName) {
        return new User(username, firstName, lastName);
    }
}
