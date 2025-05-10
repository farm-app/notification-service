package ru.rtln.notificationservice.queue.model.payload.report;

import lombok.Builder;
import lombok.Value;
import ru.rtln.notificationservice.queue.model.payload.base.PayloadModel;

import java.util.UUID;

@Value
@Builder
public class DailyEmployeeScoreReport implements PayloadModel {

    UUID uuid;

    Long userId;

    Double efficiency;
}
