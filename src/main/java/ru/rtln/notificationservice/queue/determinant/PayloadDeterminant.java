package ru.rtln.notificationservice.queue.determinant;

import lombok.Getter;
import org.springframework.util.StringUtils;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreAnalyticsReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyEmployeeScoreReport;
import ru.rtln.notificationservice.queue.model.payload.report.DailyProductReport;

/**
 * Kafka message entity type.
 */
@Getter
public enum PayloadDeterminant {


    DAILY_PRODUCT_REPORT(DailyProductReport.class),
    DAILY_EMPLOYEE_ANALYTICS_REPORT(DailyEmployeeAnalyticsReport.class),
    DAILY_EMPLOYEE_SCORE_ANALYTICS_REPORT(DailyEmployeeScoreAnalyticsReport.class),
    DAILY_EMPLOYEE_SCORE_REPORT(DailyEmployeeScoreReport.class);


    private final Class<?> payloadType;

    /**
     * @param payloadType class for deserialization
     */
    PayloadDeterminant(Class<?> payloadType) {
        this.payloadType = payloadType;
    }

    /**
     * Представление ключа события имеет вид: НАЗВАНИЕ_ID. Для определения: поддерживается ли событие -
     * сравнивается количество нижних подчеркиваний. "-1" отвечает за нижнее подчеркивание из формата ключа, его не
     * учитываем при сравнении типов.
     *
     * @param key ключ события из kafka
     * @return false - событие не найдено среди поддерживаемых к обработке.
     */
    public static boolean keyIncluded(String key) {
        if (key == null) {
            return false;
        }
        for (var type : values()) {
            if (key.toUpperCase().startsWith(type.name())) {
                int countRealChars = StringUtils.countOccurrencesOf(type.name(), "_");
                int countCharsInKey = StringUtils.countOccurrencesOf(key, "_");
                if (countCharsInKey - 1 == countRealChars) {
                    return true;
                }
            }
        }
        return false;
    }

    public static PayloadDeterminant fromEntityType(String entityType) {
        if (entityType == null) {
            return null;
        }
        for (final var v : values()) {
            if (v.name().equalsIgnoreCase(entityType)) {
                return v;
            }
        }
        return null;
    }
}
