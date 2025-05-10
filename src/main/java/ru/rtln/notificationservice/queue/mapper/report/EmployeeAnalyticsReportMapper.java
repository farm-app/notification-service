package ru.rtln.notificationservice.queue.mapper.report;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.rtln.notificationservice.entity.SystemEvent;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyProductReport;
import ru.rtln.notificationservice.util.CsvUtil;
import ru.rtln.notificationservice.util.enumeration.SystemEventType;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public abstract class EmployeeAnalyticsReportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "entityId", source = "reportId")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "content", source = "payload", qualifiedByName = "contentToEntity")
    public abstract SystemEvent toEntity(String reportId, SystemEventType type, DailyEmployeeAnalyticsReport payload);

    @Named("contentToEntity")
    protected String generateTitleToEntity(DailyEmployeeAnalyticsReport payload) {
        return CsvUtil.getCsvFromAnalyticDetails(payload.getReport());
    }

}
