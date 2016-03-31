package com.clean.example.entrypoints.job.user;

import com.clean.example.core.domain.User;
import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import com.clean.example.entrypoints.job.JobResults;
import com.clean.example.entrypoints.job.ScheduledJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindAllUsersJob implements ScheduledJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindAllUsersJob.class);

    private final FindAllUsersUseCase findAllUsersUseCase;
    private final JobResults jobResults;

    public FindAllUsersJob(FindAllUsersUseCase findAllUsersUseCase, JobResults jobResults) {
        this.findAllUsersUseCase = findAllUsersUseCase;
        this.jobResults = jobResults;
    }

    @Override
    public String getName() {
        return "FindAllUsersJob";
    }

    @Override
    public long getInitialDelay() {
        return 0;
    }

    @Override
    public long getPeriod() {
        return 5;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.SECONDS;
    }

    @Override
    public void run() {
        LOGGER.info("Finding all users from scheduled jobs...");
        List<User> allUsers = findAllUsersUseCase.findAllUsers();
        LOGGER.info("Found users: {}", allUsers);
        jobResults.recordJobResults(this, jobResults.createJobResultsCount());
    }

}
