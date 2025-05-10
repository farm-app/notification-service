package ru.rtln.notificationservice.queue.provider;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.rtln.notificationservice.entity.SystemEvent;
import ru.rtln.notificationservice.entity.SystemEventParticipant;
import ru.rtln.notificationservice.util.enumeration.SystemEventType;

import java.util.Collection;
import java.util.List;

@Validated
public interface BaseProvider {

    SystemEvent saveSystemEvent(@NotNull SystemEvent model);

    SystemEventParticipant saveSystemEventParticipant(
            @NotNull Long userId,
            @NotNull SystemEvent systemEventPersisted
    );

    List<SystemEventParticipant> saveSystemEventParticipants(
            @NotNull Collection<Long> userIds,
            @NotNull SystemEvent systemEventPersisted
    );

}
