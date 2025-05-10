package ru.rtln.notificationservice.queue.provider.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rtln.notificationservice.entity.SystemEvent;
import ru.rtln.notificationservice.entity.SystemEventParticipant;
import ru.rtln.notificationservice.queue.provider.BaseProvider;
import ru.rtln.notificationservice.repository.SystemEventParticipantRepository;
import ru.rtln.notificationservice.repository.SystemEventRepository;
import ru.rtln.notificationservice.util.enumeration.SystemEventType;

import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BaseProviderImpl implements BaseProvider {

    private final SystemEventRepository systemEventRepository;
    private final SystemEventParticipantRepository systemEventParticipantRepository;

    @Override
    @Transactional
    public SystemEvent saveSystemEvent(SystemEvent systemEvent) {
        return systemEventRepository.save(systemEvent);
    }

    @Override
    @Transactional
    public SystemEventParticipant saveSystemEventParticipant(Long userId, SystemEvent systemEventPersisted) {
        var systemEventParticipant = new SystemEventParticipant(userId, systemEventPersisted);
        return systemEventParticipantRepository.saveAndFlush(systemEventParticipant);
    }

    @Override
    @Transactional
    public List<SystemEventParticipant> saveSystemEventParticipants(Collection<Long> userIds, SystemEvent systemEventPersisted) {
        return userIds.stream()
                .map(id -> saveSystemEventParticipant(id, systemEventPersisted))
                .toList();
    }
}
