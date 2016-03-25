package com.clean.example.configuration;

import com.clean.example.core.usecase.user.FindAllUsersUseCase;
import com.clean.example.entrypoints.job.JobResults;
import com.clean.example.entrypoints.job.ScheduledJob;
import com.clean.example.entrypoints.job.user.FindAllUsersJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class JobConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobConfiguration.class);

    @Bean
    public ScheduledExecutorService scheduledExecutorService(ScheduledJob... jobs) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        for (ScheduledJob job : jobs) {
            long initialDelay = job.getInitialDelay();
            long period = job.getPeriod();
            TimeUnit timeUnit = job.getTimeUnit();
            LOGGER.info("Scheduling job {} to run every {} {}, after an initial {} {}", job.getName(), period, timeUnit, initialDelay, timeUnit);
            scheduledExecutorService.scheduleAtFixedRate(job, initialDelay, period, timeUnit);
        }

        return scheduledExecutorService;
    }

    @Bean
    public JobResults jobResults() {
        return new JobResults();
    }

    @Bean
    public ScheduledJob findAllUsersJob(FindAllUsersUseCase findAllUsersUseCase, JobResults jobResults) {
        return new FindAllUsersJob(findAllUsersUseCase, jobResults);
    }

}
