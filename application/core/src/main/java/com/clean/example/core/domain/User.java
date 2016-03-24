package com.clean.example.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

    private final String username;
    private final String firstName;
    private final String lastName;

    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
