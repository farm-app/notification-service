package ru.rtln.notificationservice.queue.model.payload.report;

import lombok.Builder;
import lombok.Value;
import ru.rtln.notificationservice.queue.model.payload.base.PayloadModel;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class DailyEmployeeAnalyticsReport implements PayloadModel {

    UUID uuid;

    List<AnalyticDetailsDto> report;
}
