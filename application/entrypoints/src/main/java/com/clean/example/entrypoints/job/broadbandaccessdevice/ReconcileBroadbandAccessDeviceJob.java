package com.clean.example.entrypoints.job.broadbandaccessdevice;

import com.clean.example.entrypoints.job.JobResults;
import com.clean.example.entrypoints.job.ScheduledJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ReconcileBroadbandAccessDeviceJob implements ScheduledJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReconcileBroadbandAccessDeviceJob.class);

    private final JobResults jobResults;

    public ReconcileBroadbandAccessDeviceJob(JobResults jobResults) {
        this.jobResults = jobResults;
    }

    @Override
    public String getName() {
        return "ReconcileBroadbandAccessDeviceJob";
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
        LOGGER.info("Running...TODO");
    }

}
