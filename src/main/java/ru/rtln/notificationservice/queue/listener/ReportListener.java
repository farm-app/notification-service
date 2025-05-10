package ru.rtln.notificationservice.queue.listener;

import ru.rtln.notificationservice.queue.model.event.CreateEvent;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyProductReport;

public interface ReportListener {

    void createDailyReportPayload(CreateEvent<DailyProductReport> payload);

    void createDailyEmployeeScoreReportPayload(CreateEvent<DailyEmployeeScoreReport> payload);

    void createDailyEmployeeScoreAnalyticsReportPayload(CreateEvent<DailyEmployeeScoreAnalyticsReport> payload);

    void createEmployeeAnalyticsReportPayload(CreateEvent<DailyEmployeeAnalyticsReport> payload);

}
