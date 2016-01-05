package ua.alex.source.webtester.components.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.alex.source.webtester.service.AccountService;

import java.sql.Timestamp;
import java.util.Calendar;

@Component
public class CheckConfirmAccountSchedule {
    private static final Logger LOGGER = Logger.getLogger(CheckConfirmAccountSchedule.class);

    @Autowired
    private AccountService accountService;

    @Scheduled(cron = "0 0/5 * * * ?") // every 5 minutes
    public void check() {
        LOGGER.info("finding expired user....");

        Timestamp dayOff = getPreviouslyDay();

        LOGGER.info("checked day - " + dayOff);

        accountService.deleteExpiredAccount(dayOff);

        LOGGER.info("deleted expired account....");
    }

    private Timestamp getPreviouslyDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, -1);
        return new Timestamp(calendar.getTimeInMillis());
    }
}
