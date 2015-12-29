package ua.alex.source.webtester.components.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.alex.source.webtester.dao.AccountDao;

@Component
public class CheckConfirmAccountSchedule {
    private static final Logger LOGGER = Logger.getLogger(CheckConfirmAccountSchedule.class);

    @Autowired
    private AccountDao accountDao;

    @Scheduled(cron = "00 45 0 * * ?") // every day at 23:58:59
    public void check() {
        LOGGER.info("schedule is being run");
    }
}
