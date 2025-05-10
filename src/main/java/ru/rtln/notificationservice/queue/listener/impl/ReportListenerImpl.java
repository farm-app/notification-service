package ru.rtln.notificationservice.queue.listener.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rtln.notificationservice.queue.listener.ReportListener;
import ru.rtln.notificationservice.queue.model.event.CreateEvent;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyProductReport;
import ru.rtln.notificationservice.queue.provider.ReportProvider;
import ru.rtln.notificationservice.service.NotificationService;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportListenerImpl implements ReportListener {

    private final ReportProvider reportProvider;
    private final NotificationService notificationService;

    @EventListener
    @Transactional
    @Override
    public void createDailyReportPayload(CreateEvent<DailyProductReport> payload) {
        log.info("Daily Product Report created");
        var participants = reportProvider.saveDailyReportPayload(payload.getAttributes(), payload.getTargetObjectId());
        notificationService.sendNotifications(participants);
    }

    @EventListener
    @Transactional
    @Override
    public void createDailyEmployeeScoreReportPayload(CreateEvent<DailyEmployeeScoreReport> payload) {
        log.info("Daily Product Employee Score Report created");
        var participants = reportProvider.saveDailyEmployeeScoreReportPayload(payload.getAttributes(), payload.getTargetObjectId());
        notificationService.sendNotifications(participants);
    }

    @EventListener
    @Transactional
    @Override
    public void createDailyEmployeeScoreAnalyticsReportPayload(CreateEvent<DailyEmployeeScoreAnalyticsReport> payload) {
        log.info("Daily Product Employee Score Analytics Report created");
        var participants = reportProvider.saveDailyEmployeeScoreAnalyticsReportPayload(payload.getAttributes(), payload.getTargetObjectId());
        notificationService.sendNotifications(participants);
    }

    @EventListener
    @Transactional
    @Override
    public void createEmployeeAnalyticsReportPayload(CreateEvent<DailyEmployeeAnalyticsReport> payload) {
        log.info("Daily Product Employee Analytics Report created");
        var participants = reportProvider.saveEmployeeAnalyticsReportPayload(payload.getAttributes(), payload.getTargetObjectId());
        notificationService.sendNotifications(participants);
    }
}
