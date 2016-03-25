package com.clean.example.entrypoints.job.user;

import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FindAllUsersJobTest {

    FindAllUsersUseCase findAllUsersUseCase = mock(FindAllUsersUseCase.class);

    FindAllUsersJob findAllUsersJob = new FindAllUsersJob(findAllUsersUseCase);

    @Test
    public void findsAllUsersWhenTheJobRuns() throws Exception {
        findAllUsersJob.run();

        verify(findAllUsersUseCase).findAllUsers();
    }

}