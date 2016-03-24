package com.clean.example.dataproviders.database.user;

import com.clean.example.core.domain.User;
import com.clean.example.core.usecase.user.FindAllUsers;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindAllUsersFromDatabaseDataProvider implements FindAllUsers {

    private JdbcTemplate jdbcTemplate;

    public FindAllUsersFromDatabaseDataProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAllUsers() {
        // TODO use jdbi instead
        List<Map<String, Object>> queryResults = jdbcTemplate.queryForList("SELECT username, first_name, last_name FROM clean_architecture.user");

        List<User> examples = new ArrayList<>();

        for (Map<String, Object> queryResult : queryResults) {
            String username = (String) queryResult.get("username");
            String firstName = (String) queryResult.get("first_name");
            String lastName = (String) queryResult.get("last_name");
            examples.add(new User(username, firstName, lastName));
        }

        return examples;
    }

}
