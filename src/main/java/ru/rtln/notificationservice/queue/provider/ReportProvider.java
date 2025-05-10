package ru.rtln.notificationservice.queue.provider;

import ru.rtln.notificationservice.entity.SystemEventParticipant;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyProductReport;

import java.util.List;

public interface ReportProvider {

    List<SystemEventParticipant> saveDailyReportPayload(DailyProductReport payload, String reportId);

    List<SystemEventParticipant> saveDailyEmployeeScoreReportPayload(DailyEmployeeScoreReport payload, String reportId);

    List<SystemEventParticipant> saveDailyEmployeeScoreAnalyticsReportPayload(DailyEmployeeScoreAnalyticsReport payload, String reportId);

    List<SystemEventParticipant> saveEmployeeAnalyticsReportPayload(DailyEmployeeAnalyticsReport payload, String reportId);
}
