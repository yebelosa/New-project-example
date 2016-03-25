package com.clean.example.entrypoints.job.user;

import com.clean.example.entrypoints.job.ScheduledJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindAllUsersJob implements ScheduledJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindAllUsersJob.class);

    @Override
    public void run() {
        LOGGER.info("Finding all users from scheduled jobs...");
    }

}
