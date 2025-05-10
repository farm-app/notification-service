package ru.rtln.notificationservice.util.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static ru.rtln.notificationservice.util.enumeration.SystemEventCategoryType.DEFAULT;
import static ru.rtln.notificationservice.util.enumeration.SystemEventCategoryType.REPORT;

@Getter
public enum SystemEventType {

    DEFAULT_TYPE(0, DEFAULT, EMPTY),

    DAILY_PRODUCT_REPORT(1, REPORT, "Ежедневный отчет о собранных продуктах"),
    DAILY_EMPLOYEE_ANALYTICS(2, REPORT, "Ежедневный аналитический отчет о выполненных рабочих нормах"),
    DAILE_EMPLOYEE_SCORE_ANALYTICS(3, REPORT, "Ежедневный аналитический отчет с оценками всех сотрудников"),
    DAILY_EMPLOYEE_SCORE(4, REPORT, "Ежедневный персональный отчет для сотрудника с его оценкой за день"),
    ;

    private final Integer id;
    private final SystemEventCategoryType category;
    private final String displayMessage;

    SystemEventType(Integer id, SystemEventCategoryType category, String displayMessage) {
        this.id = id;
        this.category = category;
        this.displayMessage = displayMessage;
    }

    public Short getId() {
        return id.shortValue();
    }

    public static SystemEventType getValueFromId(Short value) {
        if (value == null) {
            return null;
        }
        for (final SystemEventType type : SystemEventType.values()) {
            if (value.equals(type.getId())) {
                return type;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return name().toLowerCase();
    }
}
