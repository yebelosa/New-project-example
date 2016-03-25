package com.clean.example.configuration;

import com.clean.example.entrypoints.job.ScheduledJob;
import com.clean.example.entrypoints.job.user.FindAllUsersJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class JobConfiguration {

    @Bean
    public ScheduledExecutorService scheduledExecutorService(ScheduledJob job) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        long initialDelay = 0;
        long period = 5;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        scheduledExecutorService.scheduleAtFixedRate(job, initialDelay, period, timeUnit);

        return scheduledExecutorService;
    }

    @Bean
    public ScheduledJob findAllUsersJob() {
        return new FindAllUsersJob();
    }

}
