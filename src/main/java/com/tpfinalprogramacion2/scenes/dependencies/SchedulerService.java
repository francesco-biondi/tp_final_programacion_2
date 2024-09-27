package com.tpfinalprogramacion2.scenes.dependencies;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SchedulerService {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static ScheduledExecutorService getScheduler() {
        return scheduler;
    }
}


