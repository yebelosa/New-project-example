package com.clean.example.entrypoints.rest.user;

import com.clean.example.core.domain.User;
import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import com.clean.example.core.usecase.user.NoUsersFoundException;
import com.clean.example.entrypoints.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class FindAllUsersEndpoint {

    public static final String API_PATH = "/hello";

    private static final Logger LOGGER = LoggerFactory.getLogger(FindAllUsersEndpoint.class);

    private FindAllUsersUseCase helloWorldUseCase;

    public FindAllUsersEndpoint(FindAllUsersUseCase helloWorldUseCase) {
        this.helloWorldUseCase = helloWorldUseCase;
    }

    @RequestMapping(value = API_PATH, method = GET)
    public List<UserDto> getAllUsers(){
        LOGGER.info("Find all users API called...");

        try {
            List<User> examples = helloWorldUseCase.findAllUsers();
            return toDtos(examples);
        } catch (NoUsersFoundException e) {
            LOGGER.info("No users found", e);
            throw new NotFoundException();
        }
    }

    private List<UserDto> toDtos(List<User> allUsers) {
        return allUsers.stream()
                .map(user -> new UserDto(user.getFirstName(), user.getLastName()))
                .collect(Collectors.toList());
    }

}
