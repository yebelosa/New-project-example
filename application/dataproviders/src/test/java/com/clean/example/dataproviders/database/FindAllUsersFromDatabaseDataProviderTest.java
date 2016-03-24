package com.clean.example.dataproviders.database;

import com.clean.example.core.domain.User;
import com.clean.example.dataproviders.database.user.FindAllUsersFromDatabaseDataProvider;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindAllUsersFromDatabaseDataProviderTest {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

    FindAllUsersFromDatabaseDataProvider findAllUsersFromDatabaseDataProvider = new FindAllUsersFromDatabaseDataProvider(jdbcTemplate);

    @Test
    public void findsAllUsersInDatabase() throws Exception {
        givenThereAreUsersInDatabase(
                userRow("username1", "FirstName1", "LastName1"),
                userRow("username2", "FirstName2", "LastName2")
        );

        List<User> allUsers = findAllUsersFromDatabaseDataProvider.findAllUsers();

        assertThat(allUsers).hasSize(2);
        thenUserHasBeenReturned(allUsers, "username1", "FirstName1", "LastName1");
        thenUserHasBeenReturned(allUsers, "username2", "FirstName2", "LastName2");
    }

    private void givenThereAreUsersInDatabase(Map<String, Object>... users) {
        when(jdbcTemplate.queryForList(anyString())).thenReturn(Arrays.asList(users));
    }

    private Map<String, Object> userRow(String username, String firstName, String lastName) {
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("first_name", firstName);
        user.put("last_name", lastName);
        return user;
    }

    private void thenUserHasBeenReturned(List<User> allUsers, String username, String firstName, String lastName) {
        boolean containsUser = allUsers.stream().anyMatch(user ->
                user.getUsername().equals(username)
                        && user.getFirstName().equals(firstName)
                        && user.getLastName().equals(lastName));
        assertThat(containsUser).isTrue();
    }

}