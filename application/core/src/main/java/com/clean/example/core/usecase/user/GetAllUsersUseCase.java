package com.clean.example.core.usecase.user;

import com.clean.example.core.domain.User;

import java.util.List;

public class GetAllUsersUseCase {

    private final GetAllUsers getAllUsers;

    public GetAllUsersUseCase(GetAllUsers getAllUsers) {
        this.getAllUsers = getAllUsers;
    }

    public List<User> getAllUsers() {
        return getAllUsers.getAllUsers();
    }

}
