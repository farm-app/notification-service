package ru.rtln.notificationservice.queue.determinant;

import lombok.Getter;
import ru.rtln.notificationservice.queue.model.event.CreateEvent;

@Getter
public enum EventDeterminant {

    CREATE("OBJECT_CREATED", CreateEvent.class);

    private final String eventName;
    private final Class<?> eventType;

    /**
     * @param eventType class for deserialization
     */
    EventDeterminant(String eventName, Class<?> eventType) {
        this.eventName = eventName;
        this.eventType = eventType;
    }

    public static EventDeterminant fromEventName(String value) {
        if (value == null) {
            return null;
        }
        for (final var v : values()) {
            if (v.getEventName().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return null;
    }
}
