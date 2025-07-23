package com.sakinr.airportreservationsystem.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

// You can use the scehedule your some static jobs
// You can visit the belov site to calculate cron job schedule time
// https://crontab.guru/#*_*_*_*_*
@Component
@Slf4j
public class Scheduler {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    // fixed job, runs on every 120 seconds
    @Scheduled(fixedRate = 1200000)
    public void fixedRateSch() {
        Date now = new Date();
        String strDate = sdf.format(now);
        log.info("Fixed-Rate scheduler at :: " + strDate);
    }

    // cron , runs on every 15th, 30th nad 45th seconds
    // you can give a range via "-" char and so
    // format : (second) (minute) (hour) (day of month) (month) (day of week)
    @Scheduled(cron = "0 0/5 * * * *")
    public void cronJobSch() {
        Date now = new Date();
        String strDate = sdf.format(now);
        log.info("Cron-job expression at :: " + strDate);
    }

}
