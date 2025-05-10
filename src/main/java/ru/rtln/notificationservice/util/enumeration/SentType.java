package ru.rtln.notificationservice.util.enumeration;

import lombok.Getter;

@Getter
public enum SentType {

    SINGLE(0, "Новое уведомление"),
    BATCH(1, "Сводка уведомлений");

    private final Integer id;
    private final String name;

    SentType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Short getId() {
        return id.shortValue();
    }

    public static SentType getValueFromId(Short value) {
        if (value == null) {
            return null;
        }
        for (final SentType type : SentType.values()) {
            if (value.equals(type.getId())) {
                return type;
            }
        }
        return null;
    }
}
