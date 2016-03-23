package com.clean.example.dataproviders.database;

import com.clean.example.core.domain.Example;
import com.clean.example.core.usecase.GetAllExamples;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllExamplesFromDatabaseDataProvider implements GetAllExamples {

    private JdbcTemplate jdbcTemplate;

    public GetAllExamplesFromDatabaseDataProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Example> getAllExamples() {
        // TODO use jdbi instead
        List<Map<String, Object>> queryResults = jdbcTemplate.queryForList("select id, hostname from example_table");

        List<Example> examples = new ArrayList<>();

        for (Map<String, Object> queryResult : queryResults) {
            String hostname = (String) queryResult.get("HOSTNAME");
            examples.add(new Example(hostname));
        }

        return examples;
    }

}
