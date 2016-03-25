package com.clean.example.entrypoints.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobResults {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobResults.class);

    public void recordJobResults(ScheduledJob job, int numberOfSuccesses, int numberOfFailures) {
        LOGGER.info("{} finished, recording results: {} successes, {} failures", job.getName(), numberOfSuccesses, numberOfFailures);
        // do nothing for now; eventually this could save results into a database, or send them to another app, or anything really
    }

}
