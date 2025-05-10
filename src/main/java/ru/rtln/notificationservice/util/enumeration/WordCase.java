package ru.rtln.notificationservice.util.enumeration;

import lombok.Getter;

@Getter
public enum WordCase {

    NOMINATIVE(1, "Именительный"),
    GENITIVE(2, "Родительный"),
    DATIVE(3, "Дательный"),
    ACCUSATIVE(4, "Винительный"),
    ABLATIVE(5, "Творительный"),
    PREPOSITIONAL(6, "Предложный");

    private final Integer id;
    private final String displayMessage;

    WordCase(Integer id, String displayMessage) {
        this.id = id;
        this.displayMessage = displayMessage;
    }
}
