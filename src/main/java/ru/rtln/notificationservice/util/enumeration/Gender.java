package ru.rtln.notificationservice.util.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

    MALE('M'),
    FEMALE('F');

    private final Character simpleName;

    Gender(Character simpleName) {
        this.simpleName = simpleName;
    }

    @JsonValue
    public Character getSimpleName() {
        return simpleName;
    }
}
