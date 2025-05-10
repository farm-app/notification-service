package ru.rtln.notificationservice.queue.model.payload.report;

import lombok.Builder;
import lombok.Value;
import ru.rtln.notificationservice.queue.model.payload.base.PayloadModel;

import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class DailyEmployeeScoreAnalyticsReport implements PayloadModel {

    UUID uuid;

    Map<Long, Double> efficiencyByUsers;
}
