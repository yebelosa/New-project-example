package com.clean.example.integration.database.user;

import com.clean.example.configuration.DatabaseDataProviderConfiguration;
import com.clean.example.configuration.DatasourceConfiguration;
import com.clean.example.core.domain.User;
import com.clean.example.dataproviders.database.user.FindAllUsersFromDatabaseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {DatasourceConfiguration.class, DatabaseDataProviderConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class FindAllUsersDatabaseIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    FindAllUsersFromDatabaseDataProvider findAllUsersFromDatabaseDataProvider;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        cleanUpDatabase();
    }

    @Test
    public void returnsEmptyListWhenIfFindsNoUsers() throws Exception {
        givenThereAreNoUsers();

        List<User> allUsers = findAllUsersFromDatabaseDataProvider.findAllUsers();

        assertThat(allUsers).isEmpty();
    }

    @Test
    public void findAllUsers() throws Exception {
        givenUsers(
                user("username1", "first1", "last1"),
                user("username2", "first2", "last2")
        );

        List<User> allUsers = findAllUsersFromDatabaseDataProvider.findAllUsers();

        assertThat(allUsers).hasSize(2);
        thenUserHasBeenReturned(allUsers, "username1", "first1", "last1");
        thenUserHasBeenReturned(allUsers, "username2", "first2", "last2");
    }

    private void givenThereAreNoUsers() {
        cleanUpDatabase();
    }

    private void cleanUpDatabase() {
        jdbcTemplate.update("DELETE FROM clean_architecture.user");
    }

    private User user(String username, String first, String last) {
        return new User(username, first, last);
    }

    private void givenUsers(User... users) {
        int id = 1000;
        for (User user : users) {
            jdbcTemplate.update("INSERT INTO clean_architecture.user(id, username, first_name, last_name) VALUES(?, ?, ?, ?)", id++, user.getUsername(), user.getFirstName(), user.getLastName());
        }
    }

    private void thenUserHasBeenReturned(List<User> allUsers, String username, String firstName, String lastName) {
        boolean containsUser = allUsers.stream().anyMatch(user ->
                user.getUsername().equals(username)
                        && user.getFirstName().equals(firstName)
                        && user.getLastName().equals(lastName));
        assertThat(containsUser).isTrue();
    }
}
