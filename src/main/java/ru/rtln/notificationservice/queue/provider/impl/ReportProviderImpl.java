package ru.rtln.notificationservice.queue.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rtln.notificationservice.entity.SystemEventParticipant;
import ru.rtln.notificationservice.queue.mapper.report.DailyEmployeeScoreAnalyticsReportMapper;
import ru.rtln.notificationservice.queue.mapper.report.DailyEmployeeScoreReportMapper;
import ru.rtln.notificationservice.queue.mapper.report.DailyProductReportMapper;
import ru.rtln.notificationservice.queue.mapper.report.EmployeeAnalyticsReportMapper;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyProductReport;
import ru.rtln.notificationservice.queue.provider.BaseProvider;
import ru.rtln.notificationservice.queue.provider.ReportProvider;
import ru.rtln.notificationservice.redis.repository.RedisUserRepository;
import ru.rtln.notificationservice.util.enumeration.SystemEventType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReportProviderImpl implements ReportProvider {

    private final BaseProvider baseProvider;
    private final DailyProductReportMapper dailyProductReportMapper;
    private final EmployeeAnalyticsReportMapper employeeAnalyticsReportMapper;
    private final DailyEmployeeScoreReportMapper dailyEmployeeScoreReportMapper;
    private final DailyEmployeeScoreAnalyticsReportMapper dailyEmployeeScoreAnalyticsReportMapper;
    private final RedisUserRepository redisUserRepository;

    @Override
    public List<SystemEventParticipant> saveDailyReportPayload(DailyProductReport payload, String reportId) {
        var systemEventPersisted = Optional.of(payload)
                .map(a -> dailyProductReportMapper.toEntity(reportId, SystemEventType.DAILY_PRODUCT_REPORT, a))
                .map(baseProvider::saveSystemEvent)
                .orElseThrow();

        var moderatorIds = redisUserRepository.findReportModeratorIds();
        return baseProvider.saveSystemEventParticipants(moderatorIds, systemEventPersisted);
    }

    @Override
    public List<SystemEventParticipant> saveDailyEmployeeScoreReportPayload(DailyEmployeeScoreReport payload, String reportId) {
        var systemEventPersisted = Optional.of(payload)
                .map(a -> dailyEmployeeScoreReportMapper.toEntity(reportId, SystemEventType.DAILY_EMPLOYEE_SCORE, a))
                .map(baseProvider::saveSystemEvent)
                .orElseThrow();

        return baseProvider.saveSystemEventParticipants(List.of(payload.getUserId()), systemEventPersisted);
    }

    @Override
    public List<SystemEventParticipant> saveDailyEmployeeScoreAnalyticsReportPayload(DailyEmployeeScoreAnalyticsReport payload, String reportId) {
        var systemEventPersisted = Optional.of(payload)
                .map(a -> dailyEmployeeScoreAnalyticsReportMapper.toEntity(reportId, SystemEventType.DAILE_EMPLOYEE_SCORE_ANALYTICS, a))
                .map(baseProvider::saveSystemEvent)
                .orElseThrow();

        var moderatorIds = redisUserRepository.findReportModeratorIds();
        return baseProvider.saveSystemEventParticipants(moderatorIds, systemEventPersisted);
    }

    @Override
    public List<SystemEventParticipant> saveEmployeeAnalyticsReportPayload(DailyEmployeeAnalyticsReport payload, String reportId) {
        var systemEventPersisted = Optional.of(payload)
                .map(a -> employeeAnalyticsReportMapper.toEntity(reportId, SystemEventType.DAILY_EMPLOYEE_ANALYTICS, a))
                .map(baseProvider::saveSystemEvent)
                .orElseThrow();

        var moderatorIds = redisUserRepository.findReportModeratorIds();
        return baseProvider.saveSystemEventParticipants(moderatorIds, systemEventPersisted);
    }
}
