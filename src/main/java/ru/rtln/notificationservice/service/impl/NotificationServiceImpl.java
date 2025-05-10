package ru.rtln.notificationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rtln.notificationservice.entity.SystemEventParticipant;
import ru.rtln.notificationservice.repository.SystemEventParticipantRepository;
import ru.rtln.notificationservice.service.NotificationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final SystemEventParticipantRepository systemEventParticipantRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public void sendNotifications() {
        var unsentSystemEventParticipants = systemEventParticipantRepository.findUnsentSystemEventParticipants();
        sendNotifications(unsentSystemEventParticipants);
    }

    @Override
    public void sendNotifications(
            List<SystemEventParticipant> unsentSystemEventParticipants
    ) {
        for (var systemEventParticipant : unsentSystemEventParticipants) {
            eventPublisher.publishEvent(systemEventParticipant);
        }
    }

}
