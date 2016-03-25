package com.clean.example.entrypoints.job.user;

import com.clean.example.core.domain.User;
import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import com.clean.example.entrypoints.job.JobResults;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class FindAllUsersJobTest {

    JobResults jobResults = mock(JobResults.class);
    FindAllUsersUseCase findAllUsersUseCase = mock(FindAllUsersUseCase.class);

    FindAllUsersJob findAllUsersJob = new FindAllUsersJob(findAllUsersUseCase, jobResults);

    @Test
    public void recordsASuccessForEveryUserThatIfFinds() throws Exception {
        givenThereAreTwoUsers();

        findAllUsersJob.run();

        verify(jobResults).recordJobResults(findAllUsersJob, 2, 0);
    }

    private void givenThereAreTwoUsers() {
        User user1 = new User("", "", "");
        User user2 = new User("", "", "");
        when(findAllUsersUseCase.findAllUsers()).thenReturn(Arrays.asList(user1, user2));
    }

}