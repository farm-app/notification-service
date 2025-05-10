package ru.rtln.notificationservice.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.rtln.notificationservice.service.NotificationService;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationScheduler {

    private final NotificationService notificationService;

    @Value("${scheduler.enabled.notifications-send}")
    private Boolean enabled;

    @Transactional
    @Scheduled(cron = "${scheduler.cron.notifications-send}")
    public void sendNotifications() {
        sendNotifications(enabled);
    }

    @Transactional
    public void sendNotifications(Boolean enabled) {
        if (enabled) {
            notificationService.sendNotifications();
            log.info("Scheduled sending of notifications has been completed");
        } else {
            log.info("Scheduled sending of notifications is disabled");
        }
    }
}
