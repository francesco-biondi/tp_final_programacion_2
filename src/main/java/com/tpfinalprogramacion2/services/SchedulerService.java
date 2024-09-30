package com.tpfinalprogramacion2.services;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SchedulerService {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static ScheduledExecutorService getScheduler() {
        return scheduler;
    }
}


